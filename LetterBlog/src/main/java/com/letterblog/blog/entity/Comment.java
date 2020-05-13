package com.letterblog.blog.entity;



import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论
 *
 *
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -1038897351672911219L;
    private Integer commentId;

    private Integer commentPid;

    private String commentPname;

    private Integer commentArticleId;

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorAvatar;

    private String commentContent;

    private String commentAgent;

    private String commentIp;

    private Date commentCreateTime;

    /**
     * 角色
     */
    private Integer commentRole;

    /**
     * 非数据库字段
     */
    private Article article;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentPid() {
        return commentPid;
    }

    public void setCommentPid(Integer commentPid) {
        this.commentPid = commentPid;
    }

    public String getCommentPname() {
        return commentPname;
    }

    public void setCommentPname(String commentPname) {
        this.commentPname = commentPname;
    }

    public Integer getCommentArticleId() {
        return commentArticleId;
    }

    public void setCommentArticleId(Integer commentArticleId) {
        this.commentArticleId = commentArticleId;
    }

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName;
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
    }


    public String getCommentAuthorAvatar() {
        return commentAuthorAvatar;
    }

    public void setCommentAuthorAvatar(String commentAuthorAvatar) {
        this.commentAuthorAvatar = commentAuthorAvatar;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Integer getCommentRole() {
        return commentRole;
    }

    public void setCommentRole(Integer commentRole) {
        this.commentRole = commentRole;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment(Integer commentId, Integer commentPid, String commentPname, Integer commentArticleId, String commentAuthorName, String commentAuthorEmail, String commentAuthorAvatar, String commentContent, String commentAgent, String commentIp, Date commentCreateTime, Integer commentRole, Article article) {
        this.commentId = commentId;
        this.commentPid = commentPid;
        this.commentPname = commentPname;
        this.commentArticleId = commentArticleId;
        this.commentAuthorName = commentAuthorName;
        this.commentAuthorEmail = commentAuthorEmail;
        this.commentAuthorAvatar = commentAuthorAvatar;
        this.commentContent = commentContent;
        this.commentAgent = commentAgent;
        this.commentIp = commentIp;
        this.commentCreateTime = commentCreateTime;
        this.commentRole = commentRole;
        this.article = article;
    }

    public Comment() {
    }
}