package com.letterblog.blog.entity;



import java.io.Serializable;

/**
 *
 */
public class Options implements Serializable {
    private static final long serialVersionUID = -776792869602511933L;
    private Integer optionId;

    private String optionSiteTitle;

    private String optionSiteDescrption;

    private String optionMetaDescrption;

    private String optionMetaKeyword;

    private String optionAboutsiteAvatar;

    private String optionAboutsiteTitle;

    private String optionAboutsiteContent;

    private String optionAboutsiteWechat;

    private String optionAboutsiteQq;

    private String optionAboutsiteGithub;

    private String optionAboutsiteWeibo;

    private String optionTongji;

    private Integer optionStatus;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionSiteTitle() {
        return optionSiteTitle;
    }

    public void setOptionSiteTitle(String optionSiteTitle) {
        this.optionSiteTitle = optionSiteTitle;
    }

    public String getOptionSiteDescrption() {
        return optionSiteDescrption;
    }

    public void setOptionSiteDescrption(String optionSiteDescrption) {
        this.optionSiteDescrption = optionSiteDescrption;
    }

    public String getOptionMetaDescrption() {
        return optionMetaDescrption;
    }

    public void setOptionMetaDescrption(String optionMetaDescrption) {
        this.optionMetaDescrption = optionMetaDescrption;
    }

    public String getOptionMetaKeyword() {
        return optionMetaKeyword;
    }

    public void setOptionMetaKeyword(String optionMetaKeyword) {
        this.optionMetaKeyword = optionMetaKeyword;
    }

    public String getOptionAboutsiteAvatar() {
        return optionAboutsiteAvatar;
    }

    public void setOptionAboutsiteAvatar(String optionAboutsiteAvatar) {
        this.optionAboutsiteAvatar = optionAboutsiteAvatar;
    }

    public String getOptionAboutsiteTitle() {
        return optionAboutsiteTitle;
    }

    public void setOptionAboutsiteTitle(String optionAboutsiteTitle) {
        this.optionAboutsiteTitle = optionAboutsiteTitle;
    }

    public String getOptionAboutsiteContent() {
        return optionAboutsiteContent;
    }

    public void setOptionAboutsiteContent(String optionAboutsiteContent) {
        this.optionAboutsiteContent = optionAboutsiteContent;
    }

    public String getOptionAboutsiteWechat() {
        return optionAboutsiteWechat;
    }

    public void setOptionAboutsiteWechat(String optionAboutsiteWechat) {
        this.optionAboutsiteWechat = optionAboutsiteWechat;
    }

    public String getOptionAboutsiteQq() {
        return optionAboutsiteQq;
    }

    public void setOptionAboutsiteQq(String optionAboutsiteQq) {
        this.optionAboutsiteQq = optionAboutsiteQq;
    }

    public String getOptionAboutsiteGithub() {
        return optionAboutsiteGithub;
    }

    public void setOptionAboutsiteGithub(String optionAboutsiteGithub) {
        this.optionAboutsiteGithub = optionAboutsiteGithub;
    }

    public String getOptionAboutsiteWeibo() {
        return optionAboutsiteWeibo;
    }

    public void setOptionAboutsiteWeibo(String optionAboutsiteWeibo) {
        this.optionAboutsiteWeibo = optionAboutsiteWeibo;
    }

    public String getOptionTongji() {
        return optionTongji;
    }

    public void setOptionTongji(String optionTongji) {
        this.optionTongji = optionTongji;
    }

    public Integer getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(Integer optionStatus) {
        this.optionStatus = optionStatus;
    }

    public Options(Integer optionId, String optionSiteTitle, String optionSiteDescrption, String optionMetaDescrption, String optionMetaKeyword, String optionAboutsiteAvatar, String optionAboutsiteTitle, String optionAboutsiteContent, String optionAboutsiteWechat, String optionAboutsiteQq, String optionAboutsiteGithub, String optionAboutsiteWeibo, String optionTongji, Integer optionStatus) {
        this.optionId = optionId;
        this.optionSiteTitle = optionSiteTitle;
        this.optionSiteDescrption = optionSiteDescrption;
        this.optionMetaDescrption = optionMetaDescrption;
        this.optionMetaKeyword = optionMetaKeyword;
        this.optionAboutsiteAvatar = optionAboutsiteAvatar;
        this.optionAboutsiteTitle = optionAboutsiteTitle;
        this.optionAboutsiteContent = optionAboutsiteContent;
        this.optionAboutsiteWechat = optionAboutsiteWechat;
        this.optionAboutsiteQq = optionAboutsiteQq;
        this.optionAboutsiteGithub = optionAboutsiteGithub;
        this.optionAboutsiteWeibo = optionAboutsiteWeibo;
        this.optionTongji = optionTongji;
        this.optionStatus = optionStatus;
    }

    public Options() {
    }
}