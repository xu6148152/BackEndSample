package com.binea.cms.dao.model;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:11 PM
 */
public class Book {

    private Integer id;

    private Integer userid;

    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                '}';
    }
}
