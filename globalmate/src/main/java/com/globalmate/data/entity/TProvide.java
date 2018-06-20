package com.globalmate.data.entity;

import java.util.Date;

public class TProvide {
    private String id;

    private String pType;

    private String uId;

    private String uName;

    private String pLocation;

    private String pResident1;

    private String pResident2;

    private String pFeature;

    private String pEffectiveModulus;

    private Date pCreateTime;

    private Date pModifyTime;

    private String pExt1;

    private String pExt2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType == null ? null : pType.trim();
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

    public String getpLocation() {
        return pLocation;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation == null ? null : pLocation.trim();
    }

    public String getpResident1() {
        return pResident1;
    }

    public void setpResident1(String pResident1) {
        this.pResident1 = pResident1 == null ? null : pResident1.trim();
    }

    public String getpResident2() {
        return pResident2;
    }

    public void setpResident2(String pResident2) {
        this.pResident2 = pResident2 == null ? null : pResident2.trim();
    }

    public String getpFeature() {
        return pFeature;
    }

    public void setpFeature(String pFeature) {
        this.pFeature = pFeature == null ? null : pFeature.trim();
    }

    public String getpEffectiveModulus() {
        return pEffectiveModulus;
    }

    public void setpEffectiveModulus(String pEffectiveModulus) {
        this.pEffectiveModulus = pEffectiveModulus == null ? null : pEffectiveModulus.trim();
    }

    public Date getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Date pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    public Date getpModifyTime() {
        return pModifyTime;
    }

    public void setpModifyTime(Date pModifyTime) {
        this.pModifyTime = pModifyTime;
    }

    public String getpExt1() {
        return pExt1;
    }

    public void setpExt1(String pExt1) {
        this.pExt1 = pExt1 == null ? null : pExt1.trim();
    }

    public String getpExt2() {
        return pExt2;
    }

    public void setpExt2(String pExt2) {
        this.pExt2 = pExt2 == null ? null : pExt2.trim();
    }
}