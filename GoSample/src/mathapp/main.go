package main

import (
	"mymath"
	"fmt"
	"errors"
)

const (
	x = iota
	y = iota
)

func main() {
	c := 5 + 5i
	fmt.Printf("Hello, world. Sqrt(2) = %v\n", mymath.Sqrt(2))
	fmt.Printf("Value is :%v", c)
	s := `hello`
	t := []byte(s)
	t[0] = 'c'
	s2 := string(t)
	fmt.Printf("%s\n", s2)
	err := errors.New("emit macho dwarf: elf header corrupted")
	if err != nil {
		fmt.Println(err)
	}

	var arr [10]int
	arr[0] = 42
	arr[1] = 13
	fmt.Printf("%d", arr[0])

	a := [3]int{1, 2, 3}
	fmt.Println(a)

	slice := []byte{'a', 'b', 'c', 'd'}
	fmt.Println(slice)
	numbers := make(map[string]int)
	fmt.Println(numbers)
	fmt.Println(max(2, 3))
	fmt.Println(SumAndProduct(2, 3))
	x := 3
	add1(&x)
	fmt.Println(x)
	testDefer()
	testFilter()
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func SumAndProduct(A, B int) (int, int) {
	return A + B, A * B
}

func testDefer() {
	for i := 0; i < 5; i++ {
		defer fmt.Print(i)
	}
}

func myfunc(arg ...int) {

}

func add1(a *int) int {
	*a = *a + 1
	return *a
}

type testInt func(int) bool

func isOdd(integer int) bool {
	if integer%2 == 0 {
		return false
	}
	return true
}

func isEven(integer int) bool {
	if integer%2 == 0 {
		return true
	}
	return false
}

func filter(slice []int, f testInt) []int {
	var result []int
	for _, value := range slice {
		if f(value) {
			result = append(result, value)
		}
	}
	return result
}

func testFilter() {
	slice := []int {1, 2, 3, 4, 5, 6, 7}
	fmt.Println("slice = ", slice)
	odd := filter(slice, isOdd)
	fmt.Println("Odd elements of slice are: ", odd)
	even := filter(slice, isEven)
	fmt.Println("Even elements of slice are: ", even)
}