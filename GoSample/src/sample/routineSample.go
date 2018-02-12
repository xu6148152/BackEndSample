package main

import (
	"fmt"
	"runtime"
	"time"
)

func say(s string) {
	for i := 0; i < 5; i++ {
		runtime.Gosched()
		fmt.Println(s)
	}
}

func sum(a []int, c chan int) {
	total := 0
	for i, v := range a {
		fmt.Printf("index %d\n", i)
		total += v
	}
	c <- total
}

func testChannel() {
	a := []int{7, 2, 8, -9, 4, 0}
	c := make(chan int)
	go sum(a[:len(a)/2], c)
	go sum(a[len(a)/2:], c)
	x, y := <-c, <-c

	fmt.Println(x, y, x+y)
}

func testCacheChannel() {
	c := make(chan int, 2)
	c <- 1
	c <- 2
	fmt.Println(<-c)
	fmt.Println(<-c)
}

func fibonacci(c, quit chan int) {
	defer close(c)
	x, y := 1, 1
	for {
		select {
		case c <- x:
			x, y = y, x+y
		case <-time.After(5 * time.Second):
			println("timeout")
			break
		case <-quit:
			fmt.Println("quit")
			return
		}
	}
}

func testFibonacci() {
	c := make(chan int)
	quit := make(chan int)
	go func() {
		for i := 0; i < 10; i++ {
			fmt.Println(<-c)
		}
		quit <- 0
	}()
	fibonacci(c, quit)
}

func
testSay() {
	go say("hello")
	say("hello")
}

func
main() {
	//testSay()
	//testChannel()
	//testCacheChannel()
	testFibonacci()
}
