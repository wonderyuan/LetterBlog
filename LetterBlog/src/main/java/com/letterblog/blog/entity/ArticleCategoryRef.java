package com.letterblog.blog.entity;



import java.io.Serializable;

/**
 * 文章分类关联表
 *
 *
 */
public class ArticleCategoryRef implements Serializable {

    private static final long serialVersionUID = -6809206515467725995L;

    private Integer articleId;

    private Integer categoryId;

    public ArticleCategoryRef() {
    }

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
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
}