package com.binea.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by binea
 * Date: 3/11/2017
 * TIME: 9:49 PM
 */
@Component
public class Properties {
    @Value("${com.binea.name}")
    private String name;
    @Value("${com.binea.title}")
    private String title;
    @Value("${com.binea.desc}")
    private String desc;

    @Value("${com.binea.value}")
    private String value;
    @Value("${com.binea.number}")
    private String number;
    @Value("${com.binea.bignumber}")
    private Long bigNumber;
    @Value("${com.binea.test1}")
    private Integer test1;
    @Value("${com.binea.test2}")
    private Integer test2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(Long bigNumber) {
        this.bigNumber = bigNumber;
    }

    public Integer getTest1() {
        return test1;
    }

    public void setTest1(Integer test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }
}
