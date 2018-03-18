package db

import (
	"bytes"
	"encoding/json"
	"fmt"
	"log"
	"net/url"
	"os"
	"strconv"
	"strings"

	"cms/content"

	"github.com/boltdb/bolt"
	"github.com/gorilla/schema"
)

var store *bolt.DB

const dbPath string = "/GoSample/src/cms/"

func init() {
	pwd, _ := os.Getwd()

	var err error
	store, err = bolt.Open(pwd+dbPath+"store.db", 0666, nil)
	if err != nil {
		log.Fatal(err)
	}
}

func Set(target string, data url.Values) (int, error) {
	t := strings.Split(target, ":")
	ns, id := t[0], t[1]
	if len(id) == 0 {
		return insert(ns, data)
	}
	return update(ns, id, data)
}

func update(ns, id string, data url.Values) (int, error) {
	cid, err := strconv.Atoi(id)
	if err != nil {
		return 0, err
	}

	err = store.Update(func(tx *bolt.Tx) error {
		b, err := tx.CreateBucketIfNotExists([]byte(ns))
		if err != nil {
			return err
		}

		j, err := toJSON(ns, data)
		if err != nil {
			return err
		}

		err = b.Put([]byte(fmt.Sprintf("%d", cid)), j)
		if err != nil {
			return err
		}
		return nil
	})
	if err != nil {
		return 0, nil
	}

	return cid, nil
}

func insert(ns string, data url.Values) (int, error) {
	var effectedID int
	err := store.Update(func(tx *bolt.Tx) error {
		b, err := tx.CreateBucketIfNotExists([]byte(ns))
		if err != nil {
			return err
		}

		id, err := b.NextSequence()
		if err != nil {
			return err
		}

		cid := strconv.FormatUint(id, 10)
		effectedID, err = strconv.Atoi(cid)
		if err != nil {
			return err
		}

		data.Add("id", cid)

		j, err := toJSON(ns, data)
		if err != nil {
			return err
		}

		err = b.Put([]byte(cid), j)
		if err != nil {
			return err
		}

		return nil
	})

	if err != nil {
		return 0, err
	}

	return effectedID, nil
}

func toJSON(ns string, data url.Values) ([]byte, error) {
	t, ok := content.Types[ns]
	if !ok {
		return nil, fmt.Errorf(content.ErrTypeNotRegistered, ns)
	}
	post := t()

	dec := schema.NewDecoder()
	dec.SetAliasTag("json")
	dec.IgnoreUnknownKeys(true)
	err := dec.Decode(post, data)
	if err != nil {
		return nil, err
	}

	j, err := json.Marshal(post)
	if err != nil {
		return nil, err
	}

	return j, nil
}

func Get(target string) ([]byte, error) {
	t := strings.Split(target, ":")
	ns, id := t[0], t[1]
	val := &bytes.Buffer{}
	err := store.View(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(ns))
		_, err := val.Write(b.Get([]byte(id)))
		if err != nil {
			return err
		}
		return nil
	})

	if err != nil {
		return nil, err
	}

	return val.Bytes(), nil
}

func GetAll(namespace string) [][]byte {
	var posts [][]byte
	store.View(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(namespace))
		len := b.Stats().KeyN
		posts = make([][]byte, 0, len)

		b.ForEach(func(k, v []byte) error {
			posts = append(posts, v)
			return nil
		})

		return nil
	})
	return posts
}
