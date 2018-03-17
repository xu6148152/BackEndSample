package content

import (
	"fmt"

	"cms/management/editor"
)

// generic content struct
type Post struct {
	Item
	editor    editor.Editor
	Title     string `json:"title"`
	Content   string `json:"content"`
	Author    string `json:"author"`
	Timestamp string `json:"timestamp"`
}

func init() {
	Types["Post"] = func() interface{} { return new(Post) }
}

func (p *Post) ContentID() int { return p.ID }

func (p *Post) Editor() *editor.Editor { return &p.editor }

func (p *Post) MarshalEditor() ([]byte, error) {
	view, err := editor.New(p,
		editor.Field{
			View: editor.Input("Title", p, map[string]string{
				"label":       "Post Title",
				"type":        "text",
				"placeholder": "Enter your Post Title here",
			}),
		},
		editor.Field{
			View: editor.Textarea("Content", p, map[string]string{
				"label":       "Content",
				"placeholder": "Add the content of your post here",
			}),
		},
		editor.Field{
			View: editor.Input("Author", p, map[string]string{
				"label":       "Author",
				"type":        "text",
				"placeholder": "Enter the author name here",
			}),
		},
		editor.Field{
			View: editor.Input("Timestamp", p, map[string]string{
				"label": "Publish Date",
				"type":  "date",
			}),
		},
	)

	if err != nil {
		return nil, fmt.Errorf("Failed to render Post editor view: %s", err.Error())
	}

	return view, nil
}
