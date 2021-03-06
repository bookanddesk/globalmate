package com.globalmate.data.entity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.DateUtil;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.util.DataUtils;

public class Carry extends AbstractNeed{
    private String id;

    private String needId;

    private String from;

    private String to;

    private Date arrive;

    private String goodsName;

    private String brand;

    private String type;

    private String description;

    private String pic;

    private String deliveryWay;

    private String volume;

    private String weight;

    private Double rewardAmount;

    private String payway;

    private String title;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
    }

    public Date getArrive() {
        return arrive;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
        this.pic = pic == null ? null : pic.trim();
    }

    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay == null ? null : deliveryWay.trim();
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
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

    @Override
    public String getTag() {
        return NeedTypeEnum.buy.getShowValue();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(getFrom(), getTag(),
                getDescription(), getGoodsName(), getBrand());
    }

    @Override
    public String getDeparture() {
        return getFrom();
    }

    @Override
    public String getDestination() {
        return getTo();
    }

    @Override
    public String getTimeInfo() {
        return Optional.ofNullable(getArrive()).map(x -> DateUtil.format(x, DateUtil.FMT_DATETIME)).orElse(null);
    }
}