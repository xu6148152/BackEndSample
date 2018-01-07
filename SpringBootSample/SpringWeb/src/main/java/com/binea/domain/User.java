package com.binea.domain;


/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 5:30 PM
 */
public class User {

//    @Id
    private Long id;

    private String name;

    private Integer age;

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
