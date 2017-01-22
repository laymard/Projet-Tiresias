package main

import "fmt"
func f() int{
	return -5
}
func main() {
	y := 0
	z := 6
	if x := f(); x < y {
		fmt.Println(x)
	} else if x > z {
		fmt.Println(z)
	} else {
		fmt.Println(y)
	}
}