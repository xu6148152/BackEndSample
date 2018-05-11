package main

import (
	"log"
)

type Topic struct {
	name                 string
	newChannelChan       chan ChanReq
	channelMap           map[string]*Channel
	backend              NSQueue
	incomingMessageChan  chan *Message
	msgChan              chan *Message
	routerSyncChan       chan int
	readSyncChan         chan int
	exitChan             chan int
	channelWriterStarted bool
}

var topicMap = make(map[string]*Topic)
var newTopicChan = make(chan ChanReq)

// Topic constructor
func NewTopic(topicName string, inMemSize int) *Topic {
	topic := &Topic{name: topicName,
		newChannelChan:       make(chan ChanReq),
		channelMap:           make(map[string]*Channel),
		backend:              NewDiskQueue(topicName),
		incomingMessageChan:  make(chan *Message, 5),
		msgChan:              make(chan *Message, inMemSize),
		routerSyncChan:       make(chan int, 1),
		readSyncChan:         make(chan int),
		exitChan:             make(chan int),
		channelWriterStarted: false}
	go topic.Router(inMemSize)
	return topic
}

// GetTopic performs a thread safe operation
// to return a pointer to a Topic object (potentially new)
// see: topicFactory()
func GetTopic(topicName string) *Topic {
	topicChan := make(chan interface{})
	newTopicChan <- ChanReq{topicName, topicChan}
	return (<-topicChan).(*Topic)
}

// topicFactory is executed in a goroutine and manages
// the creation/retrieval of Topic objects
func topicFactory(inMemSize int) {
	var topic *Topic
	var ok bool

	for {
		topicReq := <-newTopicChan
		name := topicReq.variable.(string)
		if topic, ok = topicMap[name]; !ok {
			topic = NewTopic(name, inMemSize)
			topicMap[name] = topic
			log.Printf("TOPIC(%s): created", topic.name)
		}
		topicReq.retChan <- topic
	}
}

// GetChannel performs a thread safe operation
// to return a pointer to a Channel object (potentially new)
// for the given Topic
// see: Topic.Router()
func (t *Topic) GetChannel(channelName string) *Channel {
	channelChan := make(chan interface{})
	t.newChannelChan <- ChanReq{channelName, channelChan}
	return (<-channelChan).(*Channel)
}

// PutMessage writes to the appropriate incoming
// message channel
func (t *Topic) PutMessage(msg *Message) {
	// log.Printf("TOPIC(%s): PutMessage(%s)", t.name, string(msg.Body()))
	t.incomingMessageChan <- msg
}

// MessagePump selects over the in-memory and backend queue and 
// writes messages to every channel for this topic, synchronizing
// with the channel router
func (t *Topic) MessagePump() {
	var msg *Message

	for {
		select {
		case msg = <-t.msgChan:
		case <-t.backend.ReadReadyChan():
			buf, err := t.backend.Get()
			if err != nil {
				log.Printf("ERROR: t.backend.Get() - %s", err.Error())
				continue
			}
			msg = NewMessage(buf)
		}

		t.readSyncChan <- 1
		log.Printf("TOPIC(%s): channelMap %#v", t.name, t.channelMap)
		for _, channel := range t.channelMap {
			go func(c *Channel) {
				// log.Printf("TOPIC(%s): writing message to channel(%s)", t.name, c.name)
				c.PutMessage(msg)
			}(channel)
		}
		t.routerSyncChan <- 1
	}
}

// Router handles muxing of Topic messages including
// creation of new Channel objects, proxying messages
// to memory or backend, and synchronizing reads
func (t *Topic) Router(inMemSize int) {
	var msg *Message

	for {
		select {
		case channelReq := <-t.newChannelChan:
			name := channelReq.variable.(string)
			channel, ok := t.channelMap[name]
			if !ok {
				channel = NewChannel(name, inMemSize)
				t.channelMap[name] = channel
				log.Printf("TOPIC(%s): new channel(%s)", t.name, channel.name)
			}
			channelReq.retChan <- channel
			if !t.channelWriterStarted {
				go t.MessagePump()
				t.channelWriterStarted = true
			}
		case msg = <-t.incomingMessageChan:
			select {
			case t.msgChan <- msg:
				// log.Printf("TOPIC(%s): wrote to messageChan", t.name)
			default:
				err := t.backend.Put(msg.Data)
				if err != nil {
					log.Printf("ERROR: t.backend.Put() - %s", err.Error())
					// TODO: requeue?
				}
				// log.Printf("TOPIC(%s): wrote to backend", t.name)
			}
		case <-t.readSyncChan:
			// log.Printf("TOPIC(%s): read sync START", t.name)
			<-t.routerSyncChan
			// log.Printf("TOPIC(%s): read sync END", t.name)
		case <-t.exitChan:
			return
		}
	}
}

func (t *Topic) Close() error {
	var err error

	log.Printf("TOPIC(%s): closing", t.name)

	t.exitChan <- 1

	for _, channel := range t.channelMap {
		err = channel.Close()
		if err != nil {
			// we need to continue regardless of error to close all the channels
			log.Printf("ERROR: channel(%s) close - %s", channel.name, err.Error())
		}
	}

	err = t.backend.Close()
	if err != nil {
		return err
	}

	return nil
}
