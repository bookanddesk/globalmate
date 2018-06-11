package com.globalmate.data.entity;

import java.util.Date;

public class Userlink {
    private String id;

    private String type;

    private String enable;

    private String userId;

    private String targetId;

    private String mainTarget;

    private Date createTime;

    private Double authentication;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getMainTarget() {
        return mainTarget;
    }

    public void setMainTarget(String mainTarget) {
        this.mainTarget = mainTarget == null ? null : mainTarget.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Double authentication) {
        this.authentication = authentication;
    }
}