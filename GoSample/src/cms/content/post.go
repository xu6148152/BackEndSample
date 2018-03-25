package content

import (
	"fmt"

	"cms/management/editor"
)

// generic content struct
type Post struct {
	Item
	editor     editor.Editor
	Title      string `json:"title"`
	Content    string `json:"content"`
	Author     string `json:"author"`
	Category   string `json:"category"`
	ThemeStyle string `json:"theme"`
}

func init() {
	Types["Post"] = func() interface{} { return new(Post) }
}

func (p *Post) SetContentID(id int) { p.ID = id }

func (p *Post) ContentID() int { return p.ID }

func (p *Post) ContentName() string { return p.Title }

func (p *Post) SetSlug(slug string) { p.Slug = slug }

func (p *Post) Editor() *editor.Editor { return &p.editor }

func (p *Post) MarshalEditor() ([]byte, error) {
	view, err := editor.Form(p,
		editor.Field{
			View: editor.Checkbox("Category", p, map[string]string{
				"label": "Post Category",
			}, map[string]string{
				"important": "Important",
				"active":    "Active",
				"unplanned": "Unplanned",
			}),
		},
		editor.Field{
			View: editor.Select("ThemeStyle", p, map[string]string{
				"label": "Theme Style",
			}, map[string]string{
				"dark":  "Dark",
				"light": "Light",
			}),
		},
		editor.Field{
			View: editor.Input("Title", p, map[string]string{
				"label":       "Post Title",
				"type":        "text",
				"placeholder": "Enter your Post Title here",
			}),
		},
		editor.Field{
			View: editor.Richtext("Content", p, map[string]string{
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
	)

	if err != nil {
		return nil, fmt.Errorf("Failed to render Post editor view: %s", err.Error())
	}

	return view, nil
}
