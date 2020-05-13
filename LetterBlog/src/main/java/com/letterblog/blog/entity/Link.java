package com.letterblog.blog.entity;



import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Link implements Serializable {


    private static final long serialVersionUID = -259829372268790508L;

    private Integer linkId;

    private String linkUrl;

    private String linkName;

    private String linkImage;

    private String linkDescription;

    private String linkOwnerNickname;

    private String linkOwnerContact;

    private Date linkUpdateTime;

    private Date linkCreateTime;

    private Integer linkOrder;

    private Integer linkStatus;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

    public String getLinkOwnerNickname() {
        return linkOwnerNickname;
    }

    public void setLinkOwnerNickname(String linkOwnerNickname) {
        this.linkOwnerNickname = linkOwnerNickname;
    }

    public String getLinkOwnerContact() {
        return linkOwnerContact;
    }

    public void setLinkOwnerContact(String linkOwnerContact) {
        this.linkOwnerContact = linkOwnerContact;
    }

    public Date getLinkUpdateTime() {
        return linkUpdateTime;
    }

    public void setLinkUpdateTime(Date linkUpdateTime) {
        this.linkUpdateTime = linkUpdateTime;
    }

    public Date getLinkCreateTime() {
        return linkCreateTime;
    }

    public void setLinkCreateTime(Date linkCreateTime) {
        this.linkCreateTime = linkCreateTime;
    }

    public Integer getLinkOrder() {
        return linkOrder;
    }

    public void setLinkOrder(Integer linkOrder) {
        this.linkOrder = linkOrder;
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Link(Integer linkId, String linkUrl, String linkName, String linkImage, String linkDescription, String linkOwnerNickname, String linkOwnerContact, Date linkUpdateTime, Date linkCreateTime, Integer linkOrder, Integer linkStatus) {
        this.linkId = linkId;
        this.linkUrl = linkUrl;
        this.linkName = linkName;
        this.linkImage = linkImage;
        this.linkDescription = linkDescription;
        this.linkOwnerNickname = linkOwnerNickname;
        this.linkOwnerContact = linkOwnerContact;
        this.linkUpdateTime = linkUpdateTime;
        this.linkCreateTime = linkCreateTime;
        this.linkOrder = linkOrder;
        this.linkStatus = linkStatus;
    }

    public Link() {
    }
}