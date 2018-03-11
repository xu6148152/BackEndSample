package editor

import "bytes"

type Editable interface {
	Editor() *Editor
	NewViewBuffer()
	Render() []byte
}

type Editor struct {
	ViewBuf *bytes.Buffer
}

type Field struct {
	View []byte
}

func New(post Editable, fields ...Field) ([]byte, error) {
	post.NewViewBuffer()

	editor := post.Editor()

	for _, f := range fields {
		addFieldToEditorView(editor, f)
	}

	return post.Render(), nil
}

func addFieldToEditorView(e *Editor, f Field) {
	e.ViewBuf.Write(f.View)
}
