package com.binea.cms.dao.model;

import com.binea.pay.dao.model.Book;
import com.binea.pay.dao.model.User;

import java.util.List;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:28 PM
 */
public class UserVO extends User {

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
