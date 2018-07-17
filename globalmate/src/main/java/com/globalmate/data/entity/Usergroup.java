package com.globalmate.data.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class Usergroup {
    private String id;

    private String enable;

    private String code;

    @NotBlank(message = "组织名称不能为空")
    private String name;

    private String descrition;

    private Date createTime;

    private String creator;

    private String publicEmail;

    private String uGroupId;

    private String uPrincipalId;

    private String uPrincipalName;

    private String country;

    private String city;

    private String pic;

    private String qrCode;

    private String ext1;

    private String ext2;

    private String ext3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition == null ? null : descrition.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getPublicEmail() {
        return publicEmail;
    }

    public void setPublicEmail(String publicEmail) {
        this.publicEmail = publicEmail == null ? null : publicEmail.trim();
    }

    public String getuGroupId() {
        return uGroupId;
    }

    public void setuGroupId(String uGroupId) {
        this.uGroupId = uGroupId == null ? null : uGroupId.trim();
    }

    public String getuPrincipalId() {
        return uPrincipalId;
    }

    public void setuPrincipalId(String uPrincipalId) {
        this.uPrincipalId = uPrincipalId == null ? null : uPrincipalId.trim();
    }

    public String getuPrincipalName() {
        return uPrincipalName;
    }

    public void setuPrincipalName(String uPrincipalName) {
        this.uPrincipalName = uPrincipalName == null ? null : uPrincipalName.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}