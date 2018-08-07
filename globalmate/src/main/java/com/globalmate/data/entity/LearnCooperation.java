package com.globalmate.data.entity;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LearnCooperation extends AbstractNeed {
    private String id;

    private String needId;

    private String language;

    private String subject;

    private String description;

    private Double rewardAmount;

    private String payway;

    private String country;

    private String city;

    private String school;

    private Date startTime;

    private Date endTime;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getTag() {
        return NeedTypeEnum.learn_cooperation.getShowValue();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(getCountry(), getTag(),
                getDescription(), getTitle(), getSubject());
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
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

    @Override
    public String getTimeInfo() {
        return StringUtils.join_(Optional.ofNullable(getStartTime()).map(x -> DateUtil.format(x, DateUtil.FMT_DATETIME)).orElse(null), Optional.ofNullable(getEndTime()).map(x -> DateUtil.format(x, DateUtil.FMT_DATETIME)).orElse(null));
    }
}