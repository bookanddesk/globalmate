package com.globalmate.data.entity;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.google.common.collect.Lists;

import java.util.List;

public class NeedOther extends AbstractNeed {
    private String id;

    private String needId;

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

    public String getDescription() {
        return description;
    }

    @Override
    public String getTag() {
        return NeedTypeEnum.other.getShowValue();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(null, getTag(),
                getDescription(), getTitle());
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
        this.title = title == null ? null : title.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}