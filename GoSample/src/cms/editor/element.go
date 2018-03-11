package editor

import (
	"bytes"
	"reflect"
)

func Input(fieldName string, p interface{}, attrs map[string]string) []byte {
	var wrapInLabel = true
	label, found := attrs["label"]
	if !found {
		wrapInLabel = false
		label = ""
	}

	e := newElement("input", label, fieldName, p, attrs)

	return domElementSelfClose(e, wrapInLabel)
}

func Textarea(fieldName string, p interface{}, attrs map[string]string) []byte {
	var wrapInLabel = true
	label, found := attrs["label"]
	if !found {
		wrapInLabel = false
		label = ""
	}

	e := newElement("textarea", label, fieldName, p, attrs)

	return domElement(e, wrapInLabel)
}

type element struct {
	TagName string
	Attrs   map[string]string
	Name    string
	label   string
	data    []byte
	viewBuf *bytes.Buffer
}

// domElementSelfClose is a special DOM element which is parsed as a
// self-closing tag and thus needs to be created differently
func domElementSelfClose(e *element, wrapInLabel bool) []byte {
	if wrapInLabel {
		e.viewBuf.Write([]byte(`<label>` + e.label + `</label>`))
	}
	e.viewBuf.Write([]byte(`<` + e.TagName + ` value="`))
	e.viewBuf.Write(append(e.data, []byte(`" `)...))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + string(value) + `"`))
	}
	e.viewBuf.Write([]byte(` />`))

	return e.viewBuf.Bytes()
}

// domElement creates a DOM element
func domElement(e *element, wrapInLabel bool) []byte {
	if wrapInLabel {
		e.viewBuf.Write([]byte(`<label>` + e.label + `</label>`))
	}
	e.viewBuf.Write([]byte(`<` + e.TagName + ` `))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + string(value) + `"`))
	}
	e.viewBuf.Write([]byte(` >`))

	e.viewBuf.Write([]byte(e.data))
	e.viewBuf.Write([]byte(`</` + e.TagName + `>`))

	return e.viewBuf.Bytes()
}

func tagNameFromStructField(name string, post interface{}) string {
	field, ok := reflect.TypeOf(post).Elem().FieldByName(name)
	if !ok {
		panic("Couldn't get struct field for: " + name + ". Make sure you pass the right field name to editor field elements.")
	}

	tag, ok := field.Tag.Lookup("json")
	if !ok {
		panic("Couldn't get json struct tag for: " + name + ". Struct fields for content types must have 'json' tags.")
	}

	return tag
}

func valueFromStructField(name string, post interface{}) []byte {
	field := reflect.Indirect(reflect.ValueOf(post)).FieldByName(name)

	return field.Bytes()
}

func newElement(tagName, label, fieldName string, p interface{}, attrs map[string]string) *element {
	return &element{
		TagName: tagName,
		Attrs:   attrs,
		Name:    tagNameFromStructField(fieldName, p),
		label:   label,
		data:    valueFromStructField(fieldName, p),
		viewBuf: &bytes.Buffer{},
	}
}
