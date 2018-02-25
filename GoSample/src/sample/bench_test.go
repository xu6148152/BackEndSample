package main

import (
	"errors"
	"testing"
)

func Benchmark_Division(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Division(4, 5)
	}
}

func Benchmark_TimeConsumingFunction(b *testing.B) {
	b.StopTimer()

	b.StartTimer()
	for i := 0; i < b.N; i++ {
		Division(4, 5)
	}
}

func Division(a, b float64) (float64, error) {
	if b == 0 {
		return 0, errors.New("divisor can't be zero")
	}

	return a / b, nil
}