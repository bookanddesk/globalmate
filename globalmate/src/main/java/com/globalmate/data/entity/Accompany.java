package com.globalmate.data.entity;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class Accompany extends AbstractNeed {
    private String id;

    private String needId;

    private String where;

    private Date startTime;

    private Date endTime;

    private String description;

    private String type;

    private String doctor;

    private Double rewardAmount;

    private String payway;

    private String title;

    private String pic;

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

    public String getDescription() {
        return description;
    }

    @Override
    public String getTag() {
        return  NeedTypeEnum.accompany.getShowValue();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(getWhere(), getTag(),
                getDescription(), getTitle(), getType());
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}