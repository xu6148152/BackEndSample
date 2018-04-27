package main

import (
	"errors"
	"log"
	"time"
)

type Channel struct {
	name                string
	addClientChan       chan ChanReq
	removeClientChan    chan ChanReq
	clients             []*Client
	backend             NSQueue
	incomingMessageChan chan *Message
	msgChan             chan *Message
	inFlightMessageChan chan *Message
	requeueMessageChan  chan ChanReq
	finishMessageChan   chan ChanReq
	exitChan            chan int
	inFlightMessages    map[string]*Message
}

// Channel constructor
func NewChannel(channelName string, inMemSize int) *Channel {
	channel := &Channel{name: channelName,
		addClientChan:       make(chan ChanReq),
		removeClientChan:    make(chan ChanReq),
		clients:             make([]*Client, 0, 5),
		backend:             NewDiskQueue(channelName),
		incomingMessageChan: make(chan *Message, 5),
		msgChan:             make(chan *Message, inMemSize),
		inFlightMessageChan: make(chan *Message),
		requeueMessageChan:  make(chan ChanReq),
		finishMessageChan:   make(chan ChanReq),
		exitChan:            make(chan int),
		inFlightMessages:    make(map[string]*Message)}
	go channel.Router()
	return channel
}

// AddClient performs a thread safe operation
// to add a Client to a Channel
// see: Channel.Router()
func (c *Channel) AddClient(client *Client) {
	log.Printf("CHANNEL(%s): adding client...", c.name)
	doneChan := make(chan interface{})
	c.addClientChan <- ChanReq{client, doneChan}
	<-doneChan
}

func (c *Channel) RemoveClient(client *Client) {
	log.Printf("CHANNEL(%s): removing client...", c.name)
	doneChan := make(chan interface{})
	c.removeClientChan <- ChanReq{client, doneChan}
	<-doneChan
}

// PutMessage writes to the appropriate incoming
// message channel
func (c *Channel) PutMessage(msg *Message) {
	c.incomingMessageChan <- msg
}

func (c *Channel) FinishMessage(uuidStr string) error {
	errChan := make(chan interface{})
	c.finishMessageChan <- ChanReq{uuidStr, errChan}
	return (<-errChan).(error)
}

func (c *Channel) RequeueMessage(uuidStr string) error {
	errChan := make(chan interface{})
	c.requeueMessageChan <- ChanReq{uuidStr, errChan}
	return (<-errChan).(error)
}

// Router handles the muxing of Channel messages including
// the addition of a Client to the Channel
func (c *Channel) Router() {
	var clientReq ChanReq

	helperCloseChan := make(chan int)

	go func() {
		for {
			select {
			case msg := <-c.inFlightMessageChan:
				c.pushInFlightMessage(msg)
				go func(msg *Message) {
					select {
					case <-time.After(60 * time.Second):
						log.Printf("CHANNEL(%s): auto requeue of message(%s)", c.name, UuidToStr(msg.Uuid()))
					case <-msg.timerChan:
						return
					}
					err := c.RequeueMessage(UuidToStr(msg.Uuid()))
					if err != nil {
						log.Printf("ERROR: channel(%s) - %s", c.name, err.Error())
					}
				}(msg)
			case requeueReq := <-c.requeueMessageChan:
				uuidStr := requeueReq.variable.(string)
				msg, err := c.popInFlightMessage(uuidStr)
				if err != nil {
					log.Printf("ERROR: failed to requeue message(%s) - %s", uuidStr, err.Error())
					continue
				} else {
					go func(msg *Message) {
						c.PutMessage(msg)
					}(msg)
				}
				requeueReq.retChan <- err
			case finishReq := <-c.finishMessageChan:
				uuidStr := finishReq.variable.(string)
				_, err := c.popInFlightMessage(uuidStr)
				if err != nil {
					log.Printf("ERROR: failed to finish message(%s) - %s", uuidStr, err.Error())
				}
				finishReq.retChan <- err
			case <-helperCloseChan:
				return
			}
		}
	}()

	for {
		select {
		case clientReq = <-c.addClientChan:
			client := clientReq.variable.(*Client)
			c.clients = append(c.clients, client)
			log.Printf("CHANNEL(%s): added client(%#v)", c.name, client)
			clientReq.retChan <- 1
		case clientReq = <-c.removeClientChan:
			indexToRemove := -1
			client := clientReq.variable.(*Client)
			for k, v := range c.clients {
				if v == client {
					indexToRemove = k
					break
				}
			}
			if indexToRemove == -1 {
				log.Printf("ERROR: could not find client(%#v) in clients(%#v)", client, c.clients)
			} else {
				c.clients = append(c.clients[:indexToRemove], c.clients[indexToRemove+1:]...)
				log.Printf("CHANNEL(%s): removed client(%#v)", c.name, client)
			}
			clientReq.retChan <- 1
		case msg := <-c.incomingMessageChan:
			select {
			case c.msgChan <- msg:
				log.Printf("CHANNEL(%s): wrote to msgChan", c.name)
			default:
				err := c.backend.Put(msg.Data)
				if err != nil {
					log.Printf("ERROR: t.backend.Put() - %s", err.Error())
					// TODO: requeue?
				}
				log.Printf("CHANNEL(%s): wrote to backend", c.name)
			}
		case <-c.exitChan:
			helperCloseChan <- 1
			return
		}
	}
}

func (c *Channel) pushInFlightMessage(msg *Message) {
	uuidStr := UuidToStr(msg.Uuid())
	c.inFlightMessages[uuidStr] = msg
}

func (c *Channel) popInFlightMessage(uuidStr string) (*Message, error) {
	msg, ok := c.inFlightMessages[uuidStr]
	if !ok {
		return nil, errors.New("UUID not in flight")
	}
	delete(c.inFlightMessages, uuidStr)
	msg.EndTimer()
	return msg, nil
}

// GetMessage pulls a single message off the client channel
func (c *Channel) GetMessage() *Message {
	var msg *Message

	for {
		select {
		case msg = <-c.msgChan:
		case <-c.backend.ReadReadyChan():
			buf, err := c.backend.Get()
			if err != nil {
				log.Printf("ERROR: c.backend.Get() - %s", err.Error())
				continue
			}
			msg = NewMessage(buf)
		}
		c.inFlightMessageChan <- msg
		break
	}

	return msg
}

func (c *Channel) Close() error {
	var err error

	log.Printf("CHANNEL(%s): closing", c.name)

	c.exitChan <- 1

	// TODO: close (and wait to flush?) all clients

	err = c.backend.Close()
	if err != nil {
		return err
	}

	return nil
}
