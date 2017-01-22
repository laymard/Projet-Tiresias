package main

import "fmt"

func main() {
	defer func() {
		fmt.Println("deffered")
	}()
	fmt.Println("Before deffer")
}