package main

import (
	"fmt"
)

type Stringer interface {
	String() string
}

//func (h Human) Sing(lyrics string) {
//	fmt.Println("La la la la...", lyrics)
//}
//
//func (h Human) String() string {
//	return "❰" + h.name + " - " + strconv.Itoa(h.age) + " years -  ✆ " + h.phone + "❱"
//}
//
//func (e Employee) SayHi() {
//	fmt.Printf("Hi, I am %s, I work at %s. Call me on %s\n",
//		e.name, e.company, e.phone)
//}

type Men interface {
	SayHi()
	Sing(lyrics string)
}

type Element interface{}
type List []Element

//func testValueElement() {
//	list := make(List, 3)
//	list[0] = 1
//	list[1] = "Hello"
//	list[2] = person{"Dennis", 70}
//
//	for index, element := range list {
//		if value, ok := element.(int); ok {
//			fmt.Printf("list[%d] is an int and its value is %d\n", index, value)
//		} else if value, ok := element.(string); ok {
//			fmt.Printf("list[%d] is a string and its value is %s\n", index, value)
//		} else if value, ok := element.(person); ok {
//			fmt.Printf("list[%d] is a Person and its value is %s\n", index, value)
//		} else {
//			fmt.Printf("list[%d] is of a different type\n", index)
//		}
//	}
//}

type Animal interface {
	Speak() string
}

type Dog struct{}

func (d Dog) Speak() string {
	return "wangwang!"
}

type Cat struct{}

func (c Cat) Speak() string {
	return "miao"
}

type Pig struct{}

func (p *Pig) Speak() string {
	return "keng"
}

type Programmer struct{}

func (p Programmer) Speak() string {
	return "Hello, world"
}

func testAnimal() {
	animals := []Animal{Dog{}, Cat{}, &Pig{}, Programmer{}}
	for _, animal := range animals {
		fmt.Println(animal.Speak())
	}
}

type I interface {
	Get() string
	Set(string)
}

type S struct {
	val string
}

type T struct {
	val string
}

func (s S) Get() string {
	return s.val
}

func (s *S) Set(val string) {
	s.val = val
}

func (t T) Get() string {
	return t.val
}

func (t *T) Set(val string) {
	t.val = val
}

func testInterface() {
	var i I = &S{}
	i.Set("Hello")
	fmt.Println(i.Get())

	if t, ok := i.(*S); ok {
		fmt.Println("s implements I", t)
	}

	switch t := i.(type) {
	case *T:
		fmt.Println("i store *T", t)
	case *S:
		fmt.Println("i store *S", t)
	}
}

func printAll(vals []interface{}) {
	for _, val := range vals {
		fmt.Println(val)
	}
}

func testInterfaceSlice() {
	vals := []string{"stanley", "david", "oscar"}
	printAll(vals)
}

func main() {
	//testValueElement()
	//testInterface()
	testInterfaceSlice()
}
