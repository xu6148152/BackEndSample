package com.binea.model;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:47 PM
 */
public class CmsArticleTag {
    private Integer articleTagId;

    private Integer articleId;

    private Integer tagId;

    public Integer getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(Integer articleTagId) {
        this.articleTagId = articleTagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
