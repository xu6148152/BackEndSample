package manager

import (
	"bytes"
	"fmt"
	"html/template"
	"reflect"

	"cms/management/editor"
)

var html = `
<div class="manager">
    <form method="post" action="/admin/edit?type={{.Kind}}&contentId={{.ID}}">
        {{.Editor}}
        <input type="submit" value="Save"/>
    </form>
</div>
`

type form struct {
	ID     int
	Kind   string
	Editor template.HTML
}

func Manage(e editor.Editable) ([]byte, error) {
	v, err := e.MarshalEditor()
	if err != nil {
		return nil, fmt.Errorf("Couldn't marshal editor for content %T. %s", e, err.Error())
	}

	f := form{
		ID:     e.ContentID(),
		Kind:   reflect.TypeOf(e).Name(),
		Editor: template.HTML(v),
	}

	buf := &bytes.Buffer{}
	tmpl := template.Must(template.New("manager").Parse(html))
	tmpl.Execute(buf, f)

	return buf.Bytes(), nil
}
