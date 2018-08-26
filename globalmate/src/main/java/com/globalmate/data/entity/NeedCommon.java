package com.globalmate.data.entity;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NeedCommon extends AbstractNeed {
    private String id;

    private String needId;

    @NotBlank(message = "type can't be blank!")
    private String type;

    private Date startTime;

    private Date endTime;

    private String country;

    private String city;

    private String reward;

    private String title;

    private String description;

    private String pic;

    private String ext1;

    private String ext2;

    private String ext3;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward == null ? null : reward.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    @Override
    public String getTag() {
        return NeedTypeEnum.valueOf(type.toLowerCase()).getShowValue();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(getCountry(), getCity(), getTag(),  getTitle());
    }

    @Override
    public String getTimeInfo() {
        return StringUtils.join(new String[] {
                Optional.ofNullable(getStartTime())
                                .map(x -> DateUtil.format(x, DateUtil.FMT_DATETIME))
                                .orElse(null),
                Optional.ofNullable(getEndTime())
                        .map(x -> DateUtil.format(x, DateUtil.FMT_DATETIME))
                        .orElse(null)}, "~");
    }

    @Override
    public String getDeparture() {
        return getCountry();
    }

    @Override
    public String getDestination() {
        return getCity();
    }
}