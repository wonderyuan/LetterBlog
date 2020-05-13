package com.letterblog.blog.entity;



import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = -4901560494243593100L;
    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date noticeCreateTime;

    private Date noticeUpdateTime;

    private Integer noticeStatus;

    private Integer noticeOrder;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeCreateTime() {
        return noticeCreateTime;
    }

    public void setNoticeCreateTime(Date noticeCreateTime) {
        this.noticeCreateTime = noticeCreateTime;
    }

    public Date getNoticeUpdateTime() {
        return noticeUpdateTime;
    }

    public void setNoticeUpdateTime(Date noticeUpdateTime) {
        this.noticeUpdateTime = noticeUpdateTime;
    }

    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getNoticeOrder() {
        return noticeOrder;
    }

    public void setNoticeOrder(Integer noticeOrder) {
        this.noticeOrder = noticeOrder;
    }

    public Notice(Integer noticeId, String noticeTitle, String noticeContent, Date noticeCreateTime, Date noticeUpdateTime, Integer noticeStatus, Integer noticeOrder) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCreateTime = noticeCreateTime;
        this.noticeUpdateTime = noticeUpdateTime;
        this.noticeStatus = noticeStatus;
        this.noticeOrder = noticeOrder;
    }

    public Notice() {
    }
}