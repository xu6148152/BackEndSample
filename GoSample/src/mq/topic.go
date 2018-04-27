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

