package admin

import (
	"bytes"
	"html/template"

	"cms/content"
	"fmt"
	"cms/system/db"
)

var startAdminHTML = `<!doctype html>
<html>
    <head>
        <title>{{ .Logo }}</title>
        <style type="text/css">
            form {
                display: block;
                margin-top: 11px 0;
            }
			label {}
            input, textarea, select {
                display: block;
                margin: 11px 0 22px 0;
                padding: 2px;
            }
			input[type=checkbox] {
				dispaly: inline-block;
				margin-left: 11px;
			}
        </style>
    </head>
    <body>
        <h1><a href="/admin">CMS</a></h1>
        <div class="types">
            <ul>
            {{ range $t, $f := .Types }}
                <li><a href="/admin/posts?type={{ $t }}">{{ $t }}</a></li>
            {{ end }}
            </ul>
        </div>
        {{ if .Subview}}
        <div class="manager">
            {{ .Subview }}
        </div>
        {{ end }}
    </body>
</html>`

var mainAdminHTML = ""

var endAdminHTML = ""

type admin struct {
	Logo    string
	Types   map[string]func() interface{}
	Subview template.HTML
}

func Admin(view []byte) ([]byte, error) {
	cfg, err := db.Config("name")
	if err != nil {
		return nil, err
	}

	if cfg == nil {
		cfg = []byte("")
	}

	a := admin{
		Logo:    string(cfg),
		Types:   content.Types,
		Subview: template.HTML(view),
	}
	fmt.Println(a.Types)
	buf := &bytes.Buffer{}
	html := startAdminHTML + mainAdminHTML + endAdminHTML
	tmpl := template.Must(template.New("admin").Parse(html))
	err = tmpl.Execute(buf, a)
	if err != nil {
		return nil, err
	}

	return buf.Bytes(), nil
}

var initAdminHTML = `
<div class="init col s5">
<div class="card">
<div class="card-content">
    <div class="card-title">Welcome!</div>
    <blockquote>You need to initialize your system by filling out the form below. All of
    this information can be updated later on, but you will not be able to start
    without first completing this step.</blockquote>
    <form method="post" action="/admin/init" class="row">
        <div>Configuration</div>
        <div class="input-field col s12">
            <input placeholder="Enter the name of your site (interal use only)" class="validate required" type="text" id="name" name="name"/>
            <label for="name" class="active">Site Name</label>
        </div>
        <div class="input-field col s12">
            <input placeholder="Used for acquiring SSL certificate (e.g. www.example.com or  example.com)" class="validate" type="text" id="domain" name="domain"/>
            <label for="domain" class="active">Domain</label>
        </div>
        <div>Admin Details</div>
        <div class="input-field col s12">
            <input placeholder="Your email address e.g. you@example.com" class="validate required" type="email" id="email" name="email"/>
            <label for="email" class="active">Email</label>
        </div>
        <div class="input-field col s12">
            <input placeholder="Enter a strong password" class="validate required" type="password" id="password" name="password"/>
            <label for="password" class="active">Password</label>
        </div>
        <button class="btn waves-effect waves-light right">Start</button>
    </form>
</div>
</div>
</div>
<script>
    $(function() {
        $('.nav-wrapper ul.right').hide();

        var logo = $('a.brand-logo');
        var name = $('input#name');

        name.on('change', function(e) {
            logo.text(e.target.value);
        });
    });
</script>
`

func Init() ([]byte, error) {
	html := startAdminHTML + initAdminHTML + endAdminHTML

	cfg, err := db.Config("name")
	if err != nil {
		return nil, err
	}

	if cfg == nil {
		cfg = []byte("")
	}

	a := admin{
		Logo: string(cfg),
	}

	buf := &bytes.Buffer{}
	tmpl := template.Must(template.New("init").Parse(html))
	err = tmpl.Execute(buf, a)
	if err != nil {
		return nil, err
	}

	return buf.Bytes(), nil
}

var loginAdminHTML = `
<div class="init col s5">
<div class="card">
<div class="card-content">
    <div class="card-title">Welcome!</div>
    <blockquote>Please log in to the system using your email address and password.</blockquote>
    <form method="post" action="/admin/login" class="row">
        <div class="input-field col s12">
            <input placeholder="Enter your email address e.g. you@example.com" class="validate required" type="email" id="email" name="email"/>
            <label for="email" class="active">Email</label>
        </div>
        <div class="input-field col s12">
            <input placeholder="Enter your password" class="validate required" type="password" id="password" name="password"/>
            <label for="password" class="active">Password</label>
        </div>
        <button class="btn waves-effect waves-light right">Log in</button>
    </form>
</div>
</div>
</div>
<script>
    $(function() {
        $('.nav-wrapper ul.right').hide();
    });
</script>
`

func Login() ([]byte, error) {
	html := startAdminHTML + loginAdminHTML + endAdminHTML

	cfg, err := db.Config("name")
	if err != nil {
		return nil, err
	}

	if cfg == nil {
		cfg = []byte("")
	}

	a := admin{
		Logo: string(cfg),
	}

	buf := &bytes.Buffer{}
	tmpl := template.Must(template.New("login").Parse(html))
	err = tmpl.Execute(buf, a)
	if err != nil {
		return nil, err
	}
	return buf.Bytes(), nil
}
