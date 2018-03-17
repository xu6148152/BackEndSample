package editor

import "bytes"

type Editable interface {
	ContentID() int
	Editor() *Editor
	MarshalEditor() ([]byte, error)
}

type Editor struct {
	ViewBuf *bytes.Buffer
}

type Field struct {
	View []byte
}

func New(post Editable, fields ...Field) ([]byte, error) {
	editor := post.Editor()

	editor.ViewBuf = &bytes.Buffer{}

	for _, f := range fields {
		addFieldToEditorView(editor, f)
	}

	return editor.ViewBuf.Bytes(), nil
}

func addFieldToEditorView(e *Editor, f Field) {
	e.ViewBuf.Write(f.View)
}
