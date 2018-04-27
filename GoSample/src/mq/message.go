package main

import (
	"bytes"
	"encoding/gob"
)

type Message struct {
	// first 16 bytes are the UUID
	Data      []byte
	timerChan chan int
}

func NewMessage(data []byte) *Message {
	return &Message{data, make(chan int)}
}

func NewMessageDecoded(byteBuf []byte) (*Message, error) {
	var buf bytes.Buffer
	var msg *Message
	var err error

	_, err = buf.Write(byteBuf)
	if err != nil {
		return nil, err
	}

	decoder := gob.NewDecoder(&buf)
	err = decoder.Decode(&msg)
	if err != nil {
		return nil, err
	}

	return msg, nil
}

func (m *Message) Uuid() []byte {
	return m.Data[:16]
}

func (m *Message) Body() []byte {
	return m.Data[16:]
}

func (m *Message) EndTimer() {
	select {
	case m.timerChan <- 1:
	default:
	}
}

func (m *Message) Encode() ([]byte, error) {
	var buf bytes.Buffer

	encoder := gob.NewEncoder(&buf)
	err := encoder.Encode(*m)
	if err != nil {
		return nil, err
	}

	return buf.Bytes(), nil
}
