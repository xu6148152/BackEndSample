package com.binea.model;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:50 PM
 */
public class CmsCategoryTag {
    private Integer categoryTagId;

    private Integer categoryId;

    private Integer tagId;

    public Integer getCategoryTagId() {
        return categoryTagId;
    }

    public void setCategoryTagId(Integer categoryTagId) {
        this.categoryTagId = categoryTagId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
