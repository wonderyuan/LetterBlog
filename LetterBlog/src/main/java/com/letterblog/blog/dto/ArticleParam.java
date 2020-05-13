package com.letterblog.blog.dto;


import java.io.Serializable;
import java.util.List;

/**
 * @date 2018/11/25 下午3:56
 */
public class ArticleParam implements Serializable {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;
    private String headImg;

    private List<Integer> articleTagIds;


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getArticleParentCategoryId() {
        return articleParentCategoryId;
    }

    public void setArticleParentCategoryId(Integer articleParentCategoryId) {
        this.articleParentCategoryId = articleParentCategoryId;
    }

    public Integer getArticleChildCategoryId() {
        return articleChildCategoryId;
    }

    public void setArticleChildCategoryId(Integer articleChildCategoryId) {
        this.articleChildCategoryId = articleChildCategoryId;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public List<Integer> getArticleTagIds() {
        return articleTagIds;
    }

    public void setArticleTagIds(List<Integer> articleTagIds) {
        this.articleTagIds = articleTagIds;
    }

    public ArticleParam(Integer articleId, String articleTitle, String articleContent, Integer articleParentCategoryId, Integer articleChildCategoryId, Integer articleOrder, Integer articleStatus, List<Integer> articleTagIds) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleParentCategoryId = articleParentCategoryId;
        this.articleChildCategoryId = articleChildCategoryId;
        this.articleOrder = articleOrder;
        this.articleStatus = articleStatus;
        this.articleTagIds = articleTagIds;
    }

    public ArticleParam() {
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
