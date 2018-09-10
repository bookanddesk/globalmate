package com.globalmate.data.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class UCertifyInfo {
    private String id;

    private String uId;

    private String uName;

    private Date certifyTime;

    private int isEffective;

    @NotBlank(message = "certifyType can't be null!")
    private String cetifyType;

    @NotBlank(message = "certifyPhotoUrl can't be null!")
    private String certifyPhoto;

    private Date modifyTime;

    private String certifyInfo;

    private String cerExt1;

    private String cerExt2;

    private String cerExt3;//删除标志 0 未删除， 1 已删除

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

    public Date getCertifyTime() {
        return certifyTime;
    }

    public void setCertifyTime(Date certifyTime) {
        this.certifyTime = certifyTime;
    }

   

    public int getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(int isEffective) {
		this.isEffective = isEffective;
	}

	public String getCetifyType() {
        return cetifyType;
    }

    public void setCetifyType(String cetifyType) {
        this.cetifyType = cetifyType == null ? null : cetifyType.trim();
    }

    public String getCertifyPhoto() {
        return certifyPhoto;
    }

    public void setCertifyPhoto(String certifyPhoto) {
        this.certifyPhoto = certifyPhoto == null ? null : certifyPhoto.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCertifyInfo() {
        return certifyInfo;
    }

    public void setCertifyInfo(String certifyInfo) {
        this.certifyInfo = certifyInfo == null ? null : certifyInfo.trim();
    }

    public String getCerExt1() {
        return cerExt1;
    }

    public void setCerExt1(String cerExt1) {
        this.cerExt1 = cerExt1 == null ? null : cerExt1.trim();
    }

    public String getCerExt2() {
        return cerExt2;
    }

    public void setCerExt2(String cerExt2) {
        this.cerExt2 = cerExt2 == null ? null : cerExt2.trim();
    }

    public String getCerExt3() {
        return cerExt3;
    }

    public void setCerExt3(String cerExt3) {
        this.cerExt3 = cerExt3 == null ? null : cerExt3.trim();
    }
}