package com.globalmate.data.entity;

import java.util.Date;

public class Accompany extends BaseTypeEntity{

    private String where;

    private Date startTime;

    private Date endTime;

    private String descrition;

    private String type;

    private String doctor;


    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where == null ? null : where.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition == null ? null : descrition.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor == null ? null : doctor.trim();
    }

//    public String getNeedId() {
//        return needId;
//    }
//
//    public void setNeedId(String needId) {
//        this.needId = needId == null ? null : needId.trim();
//    }
}