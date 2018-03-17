package main

import (
	"fmt"
	"net/http"

	"cms/content"
	"cms/management/manager"
)

const (
	// ErrTypeNotRegistered means content type isn't registered (not found in content.Types map)
	ErrTypeNotRegistered = `Error:
There is no type registered for %[1]s

Add this to the file which defines %[1]s{} in the 'content' package:
--------------------------------+

func init() {
	Types["%[1]s"] = %[1]s{}
}

--------------------------------+
`
)

func main() {
	http.HandleFunc("/admin/edit", func(res http.ResponseWriter, req *http.Request) {
		err := req.ParseForm()
		if err != nil {
			res.WriteHeader(http.StatusBadRequest)
			return
		}
		switch req.Method {
		case http.MethodGet:
			t := req.FormValue("type")
			contentType, ok := content.Types[t]
			if !ok {
				fmt.Fprintf(res, ErrTypeNotRegistered, t)
				return
			}
			view, err := manager.Manage(contentType)
			if err != nil {
				res.WriteHeader(http.StatusInternalServerError)
				return
			}
			res.Header().Set("Content-Type", "text/html")
			res.Write(view)
		case http.MethodPost:
			id := req.FormValue("contentId")
			if id == "0" {
				res.Write([]byte("This would create a new post"))
				return
			}

			res.Write([]byte("Updated post " + id))
		}
	})

	http.ListenAndServe(":8080", nil)
}
