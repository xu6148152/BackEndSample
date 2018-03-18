package manager

import (
	"regexp"
	"strings"
	"unicode"

	"cms/management/editor"
	"github.com/golang/text/transform"
	"github.com/golang/text/unicode/norm"
)

func Slug(e editor.Editable) (string, error) {
	name := e.ContentName()

	// filter out non-alphanumeric character or non-whitespace
	slug, err := stringToSlug(name)
	if err != nil {
		return "", err
	}

	return slug, nil
}

func isMn(r rune) bool {
	return unicode.Is(unicode.Mn, r)
}

func stringToSlug(s string) (string, error) {
	src := []byte(strings.ToLower(s))

	// convert all spaces to dash
	rx := regexp.MustCompile("[[:space:]]")
	src = rx.ReplaceAll(src, []byte("-"))

	// remove all blanks such as tab
	rx = regexp.MustCompile("[[:blank:]]")
	src = rx.ReplaceAll(src, []byte(""))

	rx = regexp.MustCompile("[!/:-@[-`{-~]")
	src = rx.ReplaceAll(src, []byte(""))

	rx = regexp.MustCompile("/[^\x20-\x7F]/")
	src = rx.ReplaceAll(src, []byte(""))

	rx = regexp.MustCompile("`&(amp;)?#?[a-z0-9]+;`i")
	src = rx.ReplaceAll(src, []byte("-"))

	rx = regexp.MustCompile("`&([a-z])(acute|uml|circ|grave|ring|cedil|slash|tilde|caron|lig|quot|rsquo);`i")
	src = rx.ReplaceAll(src, []byte("\\1"))

	rx = regexp.MustCompile("`[^a-z0-9]`i")
	src = rx.ReplaceAll(src, []byte("-"))

	rx = regexp.MustCompile("`[-]+`")
	src = rx.ReplaceAll(src, []byte("-"))

	t := transform.Chain(norm.NFD, transform.RemoveFunc(isMn), norm.NFC)
	slug, _, err := transform.String(t, string(src))
	if err != nil {
		return "", err
	}

	return strings.TrimSpace(slug), nil
}
