package util

type ChanReq struct {
	variable interface{}
	retChan chan interface{}
}

type ChanRet struct {
	err error
	variable interface{}
}