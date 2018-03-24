package editor

import (
	"bytes"
	"reflect"
	"fmt"
	"strings"
)

type element struct {
	TagName string
	Attrs   map[string]string
	Name    string
	label   string
	data    string
	viewBuf *bytes.Buffer
}

func Select(fieldName string, p interface{}, attrs, options map[string]string) []byte {
	fieldVal := valueFromStructField(fieldName, p).String()
	attrs["class"] = "browser-default"
	sel := newElement("select", attrs["label"], fieldName, p, attrs)
	var opts []*element

	cta := &element{
		TagName: "option",
		Attrs:   map[string]string{"disabled": "true", "selected": "true"},
		viewBuf: &bytes.Buffer{},
	}

	reset := &element{
		TagName: "option",
		Attrs:   map[string]string{"value": ""},
		data:    "None",
		viewBuf: &bytes.Buffer{},
	}

	opts = append(opts, cta, reset)

	for k, v := range options {
		optAttrs := map[string]string{"value": k}
		if k == fieldVal {
			optAttrs["selected"] = "true"
		} else {
			optAttrs["selected"] = "false"
		}

		opt := &element{
			TagName: "option",
			Attrs:   optAttrs,
			data:    v,
			viewBuf: &bytes.Buffer{},
		}

		opts = append(opts, opt)
	}

	return domElementWithChildrenSelect(sel, opts)
}

func Checkbox(fieldName string, p interface{}, attrs, options map[string]string) []byte {
	attrs["class"] = "input-field col s12"
	div := newElement("div", attrs["label"], "", p, attrs)
	var opts []*element

	// get the pre-checked options if this is already an existing post
	checkedVals := valueFromStructField(fieldName, p)                         // returns refelct.Value
	checked := checkedVals.Slice(0, checkedVals.Len()).Interface().([]string) // casts reflect.Value to []string

	i := 0
	for k, v := range options {
		inputAttrs := map[string]string{
			"type":  "checkbox",
			"value": k,
			"id":    strings.Join(strings.Split(v, " "), "-"),
		}
		// check if k is in the pre-checked values and set to checked
		for _, x := range checked {
			if k == x {
				inputAttrs["checked"] = "checked"
			}
		}

		// create a *element manually using the maodified tagNameFromStructFieldMulti
		// func since this is for a multi-value name
		input := &element{
			TagName: "input",
			Attrs:   inputAttrs,
			Name:    tagNameFromStructFieldMulti(fieldName, i, p),
			label:   v,
			data:    "",
			viewBuf: &bytes.Buffer{},
		}

		opts = append(opts, input)
		i++
	}

	return domElementWithChildrenCheckbox(div, opts)
}

func Input(fieldName string, p interface{}, attrs map[string]string) []byte {
	e := newElement("input", attrs["label"], fieldName, p, attrs)

	return domElementSelfClose(e)
}

func Textarea(fieldName string, p interface{}, attrs map[string]string) []byte {

	e := newElement("textarea", attrs["label"], fieldName, p, attrs)
	return domElement(e)
}

// domElementSelfClose is a special DOM element which is parsed as a
// self-closing tag and thus needs to be created differently
func domElementSelfClose(e *element) []byte {
	e.viewBuf.Write([]byte(`<div class="input-field col s12">`))
	if e.label != "" {
		e.viewBuf.Write([]byte(`<label class="active" for="` + strings.Join(strings.Split(e.label, " "), "-") + `">` + e.label + `</label>`))
	}
	e.viewBuf.Write([]byte(`<` + e.TagName + ` value="`))
	e.viewBuf.Write([]byte(e.data + `" `))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + value + `" `))
	}
	e.viewBuf.Write([]byte(` name="` + e.Name + `"`))
	e.viewBuf.Write([]byte(` />`))

	e.viewBuf.Write([]byte(`</div>`))

	return e.viewBuf.Bytes()
}

// domElementCheckbox is a special DOM element which is parsed as a
// checkbox input tag and thus needs to be created differently
func domElementCheckbox(e *element) []byte {

	e.viewBuf.Write([]byte(`<p class="col s6">`))

	e.viewBuf.Write([]byte(`<` + e.TagName + ` `))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + value + `" `))
	}
	e.viewBuf.Write([]byte(` name="` + e.Name + `"`))
	e.viewBuf.Write([]byte(` /> `))

	if e.label != "" {
		e.viewBuf.Write([]byte(`<label for="` + strings.Join(strings.Split(e.label, " "), "-") + `">` + e.label + `</label>`))
	}

	e.viewBuf.Write([]byte(`</p>`))
	return e.viewBuf.Bytes()
}

// domElement creates a DOM element
func domElement(e *element) []byte {
	e.viewBuf.Write([]byte(`<div class="input-field col s12">`))

	if e.label != "" {
		e.viewBuf.Write([]byte(`<label class="active" for="` + strings.Join(strings.Split(e.label, " "), "-") + `">` + e.label + `</label>`))
	}
	e.viewBuf.Write([]byte(`<` + e.TagName + ` `))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + string(value) + `" `))
	}
	e.viewBuf.Write([]byte(` name="` + e.Name + `"`))
	e.viewBuf.Write([]byte(` >`))

	e.viewBuf.Write([]byte(e.data))
	e.viewBuf.Write([]byte(`</` + e.TagName + `>`))

	e.viewBuf.Write([]byte(`</div>`))

	return e.viewBuf.Bytes()
}

func domElementWithChildrenSelect(e *element, children []*element) []byte {
	e.viewBuf.Write([]byte(`<div class="input-field col s6">`))

	if e.label != "" {
		e.viewBuf.Write([]byte(`<label class="active">` + e.label + `</label>`))
	}

	e.viewBuf.Write([]byte(`</div>`))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + string(value) + `" `))
	}
	e.viewBuf.Write([]byte(` name="` + e.Name + `"`))
	e.viewBuf.Write([]byte(` >`))

	for _, child := range children {
		e.viewBuf.Write(domElement(child))
	}

	e.viewBuf.Write([]byte(`</` + e.TagName + `>`))

	if e.label != "" {
		e.viewBuf.Write([]byte(`</label>`))
	}

	return e.viewBuf.Bytes()
}

func domElementWithChildrenCheckbox(e *element, children []*element) []byte {
	e.viewBuf.Write([]byte(`<` + e.TagName + ` `))

	for attr, value := range e.Attrs {
		e.viewBuf.Write([]byte(attr + `="` + value + `" `))
	}
	e.viewBuf.Write([]byte(` >`))

	if e.label != "" {
		e.viewBuf.Write([]byte(`<label class="active">` + e.label + `</label>`))
	}

	// loop over children and create domElement for each child
	for _, child := range children {
		e.viewBuf.Write(domElementCheckbox(child))
	}

	e.viewBuf.Write([]byte(`</` + e.TagName + `><div class="clear padding">&nbsp;</div>`))

	return e.viewBuf.Bytes()
}

func tagNameFromStructField(name string, post interface{}) string {
	if name == "" {
		return name
	}
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

func tagNameFromStructFieldMulti(name string, i int, post interface{}) string {
	tag := tagNameFromStructField(name, post)
	return fmt.Sprintf("%s.%d", tag, i)
}

func valueFromStructField(name string, post interface{}) reflect.Value {
	field := reflect.Indirect(reflect.ValueOf(post)).FieldByName(name)
	return field
}

func newElement(tagName, label, fieldName string, p interface{}, attrs map[string]string) *element {
	return &element{
		TagName: tagName,
		Attrs:   attrs,
		Name:    tagNameFromStructField(fieldName, p),
		label:   label,
		data:    valueFromStructField(fieldName, p).String(),
		viewBuf: &bytes.Buffer{},
	}
}
