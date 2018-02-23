package main

import (
	_ "encoding"
	"encoding/json"
	"encoding/xml"
	"fmt"
	_ "html"
	"io/ioutil"
	"net/http"
	"os"
	"regexp"
	"strings"
	"html/template"
)

type RecurseServers struct {
	XMLName     xml.Name `xml:"servers"	`
	Version     string   `xml:"version,attr"`
	Svs         []server `xml:"server"`
	Description string   `xml: ",innerxml"`
}

type server struct {
	XMLName    xml.Name `xml:"server"`
	ServerName string   `xml:"serverName"`
	ServerIP   string   `xml:"serverIP"`
}

type Serverslice struct {
	Servers []server
}

const srcPath string = "/GoSample/src/sample/data/"

func testXml(dataPath string) {
	pwd, _ := os.Getwd()
	filePath := pwd + srcPath + dataPath
	file, err := os.Open(filePath)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}
	defer file.Close()
	data, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}

	v := RecurseServers{}
	err = xml.Unmarshal(data, &v)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}
	fmt.Println(v)
}

func testJson(dataPath string) {
	pwd, _ := os.Getwd()
	filePath := pwd + srcPath + dataPath
	file, err := os.Open(filePath)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}
	defer file.Close()
	data, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}

	v := Serverslice{}
	err = json.Unmarshal(data, &v)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}
	fmt.Println(v)
}

func testRegex() {
	resp, err := http.Get("http://www.baidu.com")
	if err != nil {
		fmt.Printf("http get error %v", err)
		return
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Printf("http read error %v", err)
		return
	}
	src := string(body)

	//transform html tag
	re, _ := regexp.Compile("\\<[\\S\\s]+?\\>")
	src = re.ReplaceAllStringFunc(src, strings.ToLower)

	// remove style
	re, _ = regexp.Compile("\\<style[\\S\\s]+?\\</style\\>")
	src = re.ReplaceAllString(src, "")

	// remove script
	re, _ = regexp.Compile("\\<script[\\S\\s]+?\\</script\\>")
	src = re.ReplaceAllString(src, "")

	//remove all bracket
	re, _ = regexp.Compile("\\<[\\S\\s]+?\\>")
	src = re.ReplaceAllString(src, "\n")

	//remove newline
	re, _ = regexp.Compile("\\s{2, }")
	src = re.ReplaceAllString(src, "\n")

	fmt.Println(strings.TrimSpace(src))
}

type Friend struct {
	Fname string
}

type PersonT struct {
	UserName string
	Emails   []string
	Friends  []*Friend
}

func testTemplate() {
	t, _ := template.New("").Parse("hello {{.UserName}}!")
	p1 := PersonT{UserName: "Binea"}
	t.Execute(os.Stdout, p1)

	f1 := Friend{Fname: "minux.ma"}
	f2 := Friend{Fname: "binea"}
	t1, _ := template.New("").Parse(`hello {{.UserName}}!
			{{range .Emails}}
				an email {{.}}
			{{end}}
			{{with .Friends}}
			{{range .}}
				my friend name is {{.Fname}}
			{{end}}
			{{end}}
			`)
	p2 := PersonT{
		UserName: "binea",
		Emails:   []string{"binea@qq.me", "binea@qq.com"},
		Friends:  []*Friend{&f1, &f2},
	}
	t1.Execute(os.Stdout, p2)

	tEmpty := template.Must(template.New("").Parse("empty pipeline if demo: {{if ``}} don't output. {{end}}\n"))
	tEmpty.Execute(os.Stdout, nil)

	tWithValue := template.Must(template.New("").Parse("empty pipeline if demo: {{if `anything`}} it has content, and will output. {{end}}\n"))
	tWithValue.Execute(os.Stdout, nil)

	tIfElse := template.Must(template.New("").Parse("if-else demo: {{if `anything`}} if part {{else}} else part. {{end}}\n"))
	tIfElse.Execute(os.Stdout, nil)
}

func EmailDealWith(args ...interface{}) string {
	ok := false
	var s string
	if len(args) == 1 {
		s, ok = args[0].(string)
	}
	if !ok {
		s = fmt.Sprint(args...)
	}
	substrs := strings.Split(s, "@")
	if len(substrs) != 2 {
		return s
	}
	return substrs[0] + " at " + substrs[1]
}

func testEmailDealWith() {
	f1 := Friend{Fname: "binea.ma"}
	f2 := Friend{Fname: "binea"}
	t := template.New("")
	t = t.Funcs(template.FuncMap{"emailDeal": EmailDealWith})
	t, _ = t.Parse(`hello {{.UserName}}!
	{{range .Emails}}
	an emails {{.|emailDeal}}
	{{end}}
	{{with .Friends}}
	{{range .}}
	my friend name is {{.Fname}}
	{{end}}
	{{end}}
	`)
	p := PersonT{UserName: "binea",
		Emails: []string{"binea@qq.me", "binea@qq.com"},
		Friends: []*Friend{&f1, &f2}}
	t.Execute(os.Stdout, p)
}

func testNestTemplate() {
	pwd, _ := os.Getwd()
	headerPath := pwd + srcPath + "header.tmpl"
	contentPath := pwd + srcPath + "content.tmpl"
	footerPath := pwd + srcPath + "footer.tmpl"
	s1, _ := template.ParseFiles(headerPath, contentPath, footerPath)
	s1.ExecuteTemplate(os.Stdout, "header", nil)
	fmt.Println()
	s1.ExecuteTemplate(os.Stdout, "content", nil)
	fmt.Println()
	s1.ExecuteTemplate(os.Stdout, "footer", nil)
	fmt.Println()
	s1.Execute(os.Stdout, nil)
}

func testMkdir() {
	os.Mkdir("binea1", 0777)
	os.MkdirAll("binea1/test1/test2", 0777)
	err := os.Remove("binea1")
	if err != nil {
		fmt.Println(err)
	}
	os.RemoveAll("binea1")
}

func testCreateFile() {
	pwd, _ := os.Getwd()
	userFile := pwd + srcPath + "binea.txt"
	fout, err := os.Create(userFile)
	if err != nil {
		fmt.Println(userFile, err)
		return
	}
	defer fout.Close()
	for i := 0; i < 10; i++ {
		fout.WriteString("Just a test!\r\n")
		fout.Write([]byte("Just a test!\r\n"))
	}

	fl, err := os.Open(userFile)
	if err != nil {
		fmt.Println(userFile, err)
		return
	}
	defer fl.Close()
	buf := make([]byte, 1024)
	for {
		n, _ := fl.Read(buf)
		if 0 == n {
			break
		}
		os.Stdout.Write(buf[:n])
	}

	os.Remove(userFile)
}
func main() {
	//testXml("servers.xml")
	//testJson("servers.json")
	//testRegex()
	//testTemplate()
	//testEmailDealWith()
	//testNestTemplate()
	//testMkdir()
	testCreateFile()
}
