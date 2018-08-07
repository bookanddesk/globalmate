package com.globalmate.data.entity;

import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.service.need.NeedTypeEnum;
import com.google.common.collect.Lists;

import java.util.List;

public class Buy extends AbstractNeed{
    private String id;

    private String needId;

    private String country;

    private String goodsName;

    private String brand;

    private String type;

    private String descrition;

    private String pic;

    private String deliveryWay;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
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

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition == null ? null : descrition.trim();
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
    public String getDeparture() {
        return getCountry();
    }

    @Override
    public String getDescription() {
        return getDescrition();
    }

    @Override
    public List<String> getKeywords() {
        return Lists.newArrayList(getCountry(), getTag(),
                getDescrition(), getGoodsName(), getBrand());
    }

    @Override
    public String getTimeInfo() {
        return null;
    }
}