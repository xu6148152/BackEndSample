package cms

import (
	"flag"
	"fmt"
	"os"
	"net/http"
)

var usage = `
$ cms <option> <params>

Options:
generate, gen, g:
    Generate a new content type file with boilerplat code to implement
    the editor.Editable interface. Must be given one (1) parameter of
    the name of the type for the new content.

    Example:
        $ cms gen Review

`

func init() {
	flag.Usage = func() {
		fmt.Println(usage)
	}
}

func main() {
	flag.Parse()

	args := flag.Args()
	if len(args) < 1 {
		flag.PrintDefaults()
		os.Exit(0)
	}

	fmt.Println(args)
	switch args[0] {
	case "new":
		if len(args) < 2 {
			flag.PrintDefaults()
			os.Exit(0)
		}
		err := newProjectInDir(args[1])
		if err != nil {
			fmt.Println(err)
			os.Exit(1)
		}
	case "generate", "gen", "g":
		if len(args) < 2 {
			flag.PrintDefaults()
			os.Exit(0)
		}

		err := generateContentType(args[1])
		if err != nil {
			fmt.Println(err)
			os.Exit(1)
		}
	case "serve", "s":
		serve()
	case "":
		flag.PrintDefaults()
	default:
		flag.PrintDefaults()
	}
}

func serve() {
	http.ListenAndServe(":8080", nil)
}
