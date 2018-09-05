package com.globalmate.data.entity;

import java.util.Date;

public class User extends BaseEntity{
    private String id;

    private String code;

    private String name;

    private String nikename;

    private String password;

    private String salt;

    private String email;

    private String phone;

    private String pic;

    private Date createTime;

    private Date modifyTime;

    private String enable;

    private String idNumber;

    private Integer nice;

    private String where;

    private String school;

    private String hobby;

    private Boolean isCertified;

    private String cetifiyId;

    private String userCredit;

    private String userTag;

    private String resident;

    private String uExt1;//最后登录时间

    private String uExt2;//会员号

    private String uExt3;

    private String country;
    private String city;
    private String helpAvailable;
    private Boolean subscribe;
    private String openid;
    private String sex;
    private String language;
    private String subscribe_scene;
    private String province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename == null ? null : nikename.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public Integer getNice() {
        return nice;
    }

    public void setNice(Integer nice) {
        this.nice = nice;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where == null ? null : where.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    public String getCetifiyId() {
        return cetifiyId;
    }

    public void setCetifiyId(String cetifiyId) {
        this.cetifiyId = cetifiyId == null ? null : cetifiyId.trim();
    }

    public String getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(String userCredit) {
        this.userCredit = userCredit == null ? null : userCredit.trim();
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag == null ? null : userTag.trim();
    }

    public String getResident() {
        return resident;
    }

    public void setResident(String resident) {
        this.resident = resident == null ? null : resident.trim();
    }

    public String getuExt1() {
        return uExt1;
    }

    public void setuExt1(String uExt1) {
        this.uExt1 = uExt1 == null ? null : uExt1.trim();
    }

    public String getuExt2() {
        return uExt2;
    }

    public void setuExt2(String uExt2) {
        this.uExt2 = uExt2 == null ? null : uExt2.trim();
    }

    public String getuExt3() {
        return uExt3;
    }

    public void setuExt3(String uExt3) {
        this.uExt3 = uExt3 == null ? null : uExt3.trim();
    }

    public Boolean getCertified() {
        return isCertified;
    }

    public void setCertified(Boolean certified) {
        isCertified = certified;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHelpAvailable() {
        return helpAvailable;
    }

    public void setHelpAvailable(String helpAvailable) {
        this.helpAvailable = helpAvailable;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubscribe_scene() {
        return subscribe_scene;
    }

    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}