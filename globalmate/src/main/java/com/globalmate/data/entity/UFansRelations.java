package com.globalmate.data.entity;

import java.util.Date;

public class UFansRelations {
    private String id;

    private String uId;

    private String uName;

    private Short relationType;

    private String uRelatedId;

    private String uRelatedName;

    private Date createTime;

    private Date modityTime;

    private Boolean isDeleted;

    private String relaExt1;

    private String relaExt2;

    private String relaExt3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public Short getRelationType() {
        return relationType;
    }

    public void setRelationType(Short relationType) {
        this.relationType = relationType;
    }

    public String getuRelatedId() {
        return uRelatedId;
    }

    public void setuRelatedId(String uRelatedId) {
        this.uRelatedId = uRelatedId == null ? null : uRelatedId.trim();
    }

    public String getuRelatedName() {
        return uRelatedName;
    }

    public void setuRelatedName(String uRelatedName) {
        this.uRelatedName = uRelatedName == null ? null : uRelatedName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModityTime() {
        return modityTime;
    }

    public void setModityTime(Date modityTime) {
        this.modityTime = modityTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRelaExt1() {
        return relaExt1;
    }

    public void setRelaExt1(String relaExt1) {
        this.relaExt1 = relaExt1 == null ? null : relaExt1.trim();
    }

    public String getRelaExt2() {
        return relaExt2;
    }

    public void setRelaExt2(String relaExt2) {
        this.relaExt2 = relaExt2 == null ? null : relaExt2.trim();
    }

    public String getRelaExt3() {
        return relaExt3;
    }

    public void setRelaExt3(String relaExt3) {
        this.relaExt3 = relaExt3 == null ? null : relaExt3.trim();
    }
}