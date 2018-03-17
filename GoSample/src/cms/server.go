package main

import (
	"fmt"
	"net/http"
	"encoding/json"

	"cms/content"
	"cms/management/manager"
	"cms/management/editor"
	"cms/system/db"
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
			q := req.URL.Query()
			i := q.Get("id")
			t := q.Get("type")
			contentType, ok := content.Types[t]
			if !ok {
				fmt.Fprintf(res, content.ErrTypeNotRegistered, t)
				return
			}
			post := contentType()

			if i != "" {
				fmt.Println("Need to show post id:", i, "(", t, ")")
				data, err := db.Get(t + ":" + i)
				if err != nil {
					fmt.Println(err)
					res.WriteHeader(http.StatusInternalServerError)
					return
				}

				err = json.Unmarshal(data, post)
				if err != nil {
					fmt.Println(err)
					res.WriteHeader(http.StatusInternalServerError)
					return
				}

			}

			view, err := manager.Manage(post.(editor.Editable), t)
			if err != nil {
				fmt.Println(err)
				res.WriteHeader(http.StatusInternalServerError)
				return
			}
			res.Header().Set("Content-Type", "text/html")
			res.Write(view)
		case http.MethodPost:
			cid := req.FormValue("id")
			t := req.FormValue("type")
			fmt.Println("query data: t=", t, "id=", cid)

			id, err := db.Set(t+":"+cid, req.PostForm)
			if err != nil {
				fmt.Println(err)
				res.WriteHeader(http.StatusInternalServerError)
				return
			}

			fmt.Println(t, "post created:", id)
			scheme := req.URL.Scheme
			host := req.URL.Host
			path := req.URL.Path
			desURL := scheme + host + path + "?type=" + t + "&id=" + fmt.Sprintf("%d", id)
			http.Redirect(res, req, desURL, http.StatusFound)
		}
	})

	http.ListenAndServe(":8080", nil)
}
