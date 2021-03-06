package com.globalmate.data.entity;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.DateUtil;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Clearance extends AbstractNeed {
    private String id;

    private String needId;

    private String where;

    private Date time;

    private String flightInformation;

    private String airport;

    private String description;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFlightInformation() {
        return flightInformation;
    }

    public void setFlightInformation(String flightInformation) {
        this.flightInformation = flightInformation == null ? null : flightInformation.trim();
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport == null ? null : airport.trim();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getTag() {
        return NeedTypeEnum.clearance.getShowValue();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(getWhere(), getTag(),
                getDescription(), getTitle(), getAirport());
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String getTimeInfo() {
        return Optional.ofNullable(getTime()).map(x -> DateUtil.format(x, DateUtil.FMT_DATETIME)).orElse(null);
    }
}