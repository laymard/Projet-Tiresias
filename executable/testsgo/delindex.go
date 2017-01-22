package main
import "fmt"

type Object struct {
	index int
}

func (o *Object) String() string {
	return fmt.Sprintf("Object: %d", o.index)
}
func main() {
	var slice []*Object
	for i := 0; i < 10; i++ {
		slice = append(slice, &Object{index: i})
	}
	delIndex := 5
	// delete tricks for slices of pointers
	// https://github.com/golang/go/wiki/SliceTricks
	slice, slice[len(slice)-1] = append(slice[:delIndex], slice[delIndex+1:]...), nil
	for i, obj := range slice {
		fmt.Println(i, obj.String())
	}
}