package com.globalmate.data.entity;

import java.util.Date;

public class SysAssistanceDeal {
    private String id;

    private String needId;

    private String uNeederId;

    private String uNeederName;

    private String provideId;

    private String uProviderId;

    private String uProviderName;

    private Date assistCreateTime;

    private Date assistModifyTime;

    private Date assistEndTime;

    private String assistStatus;

    private String assistEvaluation;

    private String assistPrice;

    private String assistExt1;

    private String assistExt2;

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

    public String getuNeederId() {
        return uNeederId;
    }

    public void setuNeederId(String uNeederId) {
        this.uNeederId = uNeederId == null ? null : uNeederId.trim();
    }

    public String getuNeederName() {
        return uNeederName;
    }

    public void setuNeederName(String uNeederName) {
        this.uNeederName = uNeederName == null ? null : uNeederName.trim();
    }

    public String getProvideId() {
        return provideId;
    }

    public void setProvideId(String provideId) {
        this.provideId = provideId == null ? null : provideId.trim();
    }

    public String getuProviderId() {
        return uProviderId;
    }

    public void setuProviderId(String uProviderId) {
        this.uProviderId = uProviderId == null ? null : uProviderId.trim();
    }

    public String getuProviderName() {
        return uProviderName;
    }

    public void setuProviderName(String uProviderName) {
        this.uProviderName = uProviderName == null ? null : uProviderName.trim();
    }

    public Date getAssistCreateTime() {
        return assistCreateTime;
    }

    public void setAssistCreateTime(Date assistCreateTime) {
        this.assistCreateTime = assistCreateTime;
    }

    public Date getAssistModifyTime() {
        return assistModifyTime;
    }

    public void setAssistModifyTime(Date assistModifyTime) {
        this.assistModifyTime = assistModifyTime;
    }

    public Date getAssistEndTime() {
        return assistEndTime;
    }

    public void setAssistEndTime(Date assistEndTime) {
        this.assistEndTime = assistEndTime;
    }

    public String getAssistStatus() {
        return assistStatus;
    }

    public void setAssistStatus(String assistStatus) {
        this.assistStatus = assistStatus == null ? null : assistStatus.trim();
    }

    public String getAssistEvaluation() {
        return assistEvaluation;
    }

    public void setAssistEvaluation(String assistEvaluation) {
        this.assistEvaluation = assistEvaluation == null ? null : assistEvaluation.trim();
    }

    public String getAssistPrice() {
        return assistPrice;
    }

    public void setAssistPrice(String assistPrice) {
        this.assistPrice = assistPrice == null ? null : assistPrice.trim();
    }

    public String getAssistExt1() {
        return assistExt1;
    }

    public void setAssistExt1(String assistExt1) {
        this.assistExt1 = assistExt1 == null ? null : assistExt1.trim();
    }

    public String getAssistExt2() {
        return assistExt2;
    }

    public void setAssistExt2(String assistExt2) {
        this.assistExt2 = assistExt2 == null ? null : assistExt2.trim();
    }
}