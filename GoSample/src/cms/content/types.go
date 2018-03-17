package content

const (
	// ErrTypeNotRegistered means content type isn't registered (not found in Types map)
	ErrTypeNotRegistered = `Error:
There is no type registered for %[1]s

Add this to the file which defines %[1]s{} in the 'content' package:
--------------------------------------------------------------------------+

func init() {
	Types["%[1]s"] = func() interface{} { return new(%[1]s) }
}

--------------------------------------------------------------------------+
`
)

var Types = make(map[string]func() interface{})
