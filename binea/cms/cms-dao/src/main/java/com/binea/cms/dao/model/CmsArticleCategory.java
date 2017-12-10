package com.binea.cms.dao.model;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:45 PM
 */
public class CmsArticleCategory {
    private Integer articleCategoryId;

    private Integer articleId;

    private Integer categoryId;

    private static final long serialVersionUID = 1L;

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "CmsArticleCategory{" +
                "articleCategoryId=" + articleCategoryId +
                ", articleId=" + articleId +
                ", categoryId=" + categoryId +
                '}';
    }
}
