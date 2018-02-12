package main

import (
	"fmt"
	"strconv"
)

type Stringer interface {
	String() string
}

func (h Human) Sing(lyrics string) {
	fmt.Println("La la la la...", lyrics)
}

func (h Human) String() string {
	return "❰" + h.name + " - " + strconv.Itoa(h.age) + " years -  ✆ " + h.phone + "❱"
}

func (e Employee) SayHi() {
	fmt.Printf("Hi, I am %s, I work at %s. Call me on %s\n",
		e.name, e.company, e.phone)
}

type Men interface {
	SayHi()
	Sing(lyrics string)
}

type Element interface{}
type List []Element

func testValueElement() {
	list := make(List, 3)
	list[0] = 1
	list[1] = "Hello"
	list[2] = person{"Dennis", 70}

	for index, element := range list {
		if value, ok := element.(int); ok {
			fmt.Printf("list[%d] is an int and its value is %d\n", index, value)
		} else if value, ok := element.(string); ok {
			fmt.Printf("list[%d] is a string and its value is %s\n", index, value)
		} else if value, ok := element.(person); ok {
			fmt.Printf("list[%d] is a Person and its value is %s\n", index, value)
		} else {
			fmt.Printf("list[%d] is of a different type\n", index)
		}
	}
}
func main() {
	testValueElement()
}
