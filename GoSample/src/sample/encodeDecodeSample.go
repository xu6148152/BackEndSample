package main

import (
	"crypto/aes"
	"crypto/cipher"
	"encoding/base64"
	"fmt"
	"os"
)

func base64Encode(src []byte) []byte {
	return []byte(base64.StdEncoding.EncodeToString(src))
}

func base64Decode(src []byte) ([]byte, error) {
	return base64.StdEncoding.DecodeString(string(src))
}

func testBase64() {
	hello := "您好，世界 ！ hello world!"
	debyte := base64Encode([]byte(hello))
	fmt.Println(debyte)

	enbyte, err := base64Decode(debyte)
	if err != nil {
		fmt.Println(err.Error())
	}

	if hello != string(enbyte) {
		fmt.Println("hello is not equal to enbyte")
	}

	fmt.Println(string(enbyte))
}

var commonIV = []byte{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f}

func testAes() {
	plaintext := []byte("My name is binea")
	if len(os.Args) > 1 {
		plaintext = []byte(os.Args[1])
	}

	keyText := "bineabc12798akljzmknm.ahkjkljl;k"
	if len(os.Args) > 2 {
		keyText = os.Args[2]
	}

	fmt.Println(len(keyText))

	c, err := aes.NewCipher([]byte(keyText))
	if err != nil {
		fmt.Printf("Error: NewClipher(%d bytes) = %s", len(keyText), err)
		os.Exit(-1)
	}

	cfb := cipher.NewCFBEncrypter(c, commonIV)
	cipherText := make([]byte, len(plaintext))
	cfb.XORKeyStream(cipherText, plaintext)
	fmt.Printf("%s=>%x\n", plaintext, cipherText)

	cfbdec := cipher.NewCFBDecrypter(c, commonIV)
	plainTextCopy := make([]byte, len(plaintext))
	cfbdec.XORKeyStream(plainTextCopy, cipherText)
	fmt.Printf("%s=>%s\n", cipherText, plainTextCopy)
}

func main() {
	//testEncodeDecode()
	testAes()
}
