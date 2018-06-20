package com.globalmate.data.entity;

import java.util.Date;

public class TNeedComments {
    private String id;

    private String needId;

    private String uNeederId;

    private String uNedderName;

    private Date commentCreateTime;

    private Date commentModifyTime;

    private Short toppedTimes;

    private Short steppedTimes;

    private Boolean isReplied;

    private String repliedCommentId;

    private Boolean isReplyComment;

    private String commentExt1;

    private String commentExt2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNeedId() {
        return needId;
    }

    public void setNeedId(String needId) {
        this.needId = needId == null ? null : needId.trim();
    }

    public String getuNeederId() {
        return uNeederId;
    }

    public void setuNeederId(String uNeederId) {
        this.uNeederId = uNeederId == null ? null : uNeederId.trim();
    }

    public String getuNedderName() {
        return uNedderName;
    }

    public void setuNedderName(String uNedderName) {
        this.uNedderName = uNedderName == null ? null : uNedderName.trim();
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Date getCommentModifyTime() {
        return commentModifyTime;
    }

    public void setCommentModifyTime(Date commentModifyTime) {
        this.commentModifyTime = commentModifyTime;
    }

    public Short getToppedTimes() {
        return toppedTimes;
    }

    public void setToppedTimes(Short toppedTimes) {
        this.toppedTimes = toppedTimes;
    }

    public Short getSteppedTimes() {
        return steppedTimes;
    }

    public void setSteppedTimes(Short steppedTimes) {
        this.steppedTimes = steppedTimes;
    }

    public Boolean getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(Boolean isReplied) {
        this.isReplied = isReplied;
    }

    public String getRepliedCommentId() {
        return repliedCommentId;
    }

    public void setRepliedCommentId(String repliedCommentId) {
        this.repliedCommentId = repliedCommentId == null ? null : repliedCommentId.trim();
    }

    public Boolean getIsReplyComment() {
        return isReplyComment;
    }

    public void setIsReplyComment(Boolean isReplyComment) {
        this.isReplyComment = isReplyComment;
    }

    public String getCommentExt1() {
        return commentExt1;
    }

    public void setCommentExt1(String commentExt1) {
        this.commentExt1 = commentExt1 == null ? null : commentExt1.trim();
    }

    public String getCommentExt2() {
        return commentExt2;
    }

    public void setCommentExt2(String commentExt2) {
        this.commentExt2 = commentExt2 == null ? null : commentExt2.trim();
    }
}