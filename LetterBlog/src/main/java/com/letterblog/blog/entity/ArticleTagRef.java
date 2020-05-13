package com.letterblog.blog.entity;



import java.io.Serializable;

/**
 * 文章和标签关联
 *
 *
 * @date 2018/11/17 下午5:20
 */
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;

    private Integer articleId;

    private Integer tagId;

    public ArticleTagRef() {
    }

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
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
