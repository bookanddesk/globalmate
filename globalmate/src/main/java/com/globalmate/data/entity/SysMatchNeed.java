package com.globalmate.data.entity;

import java.util.Date;

public class SysMatchNeed extends BaseEntity {
    private String id;

    private String needId;

    private String uNeedId;

    private String uNeedName;

    private String providerId;

    private String providerName;

    private String provideId;

    private String matchInfo;

    private Integer matchMsgCount;

    private Boolean matchAccept;

    private Date matchCreateTime;

    private Date matchModifyTime;

    private Date lastPushTime;

    private String matchExt1;

    private String matchExt2;

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

    public String getuNeedId() {
        return uNeedId;
    }

    public void setuNeedId(String uNeedId) {
        this.uNeedId = uNeedId == null ? null : uNeedId.trim();
    }

    public String getuNeedName() {
        return uNeedName;
    }

    public void setuNeedName(String uNeedName) {
        this.uNeedName = uNeedName == null ? null : uNeedName.trim();
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    public String getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(String matchInfo) {
        this.matchInfo = matchInfo == null ? null : matchInfo.trim();
    }

    public Integer getMatchMsgCount() {
        return matchMsgCount;
    }

    public void setMatchMsgCount(Integer matchMsgCount) {
        this.matchMsgCount = matchMsgCount;
    }

    public Boolean getMatchAccept() {
        return matchAccept;
    }

    public void setMatchAccept(Boolean matchAccept) {
        this.matchAccept = matchAccept;
    }

    public Date getMatchCreateTime() {
        return matchCreateTime;
    }

    public void setMatchCreateTime(Date matchCreateTime) {
        this.matchCreateTime = matchCreateTime;
    }

    public Date getMatchModifyTime() {
        return matchModifyTime;
    }

    public void setMatchModifyTime(Date matchModifyTime) {
        this.matchModifyTime = matchModifyTime;
    }

    public Date getLastPushTime() {
        return lastPushTime;
    }

    public void setLastPushTime(Date lastPushTime) {
        this.lastPushTime = lastPushTime;
    }

    public String getMatchExt1() {
        return matchExt1;
    }

    public void setMatchExt1(String matchExt1) {
        this.matchExt1 = matchExt1 == null ? null : matchExt1.trim();
    }

    public String getMatchExt2() {
        return matchExt2;
    }

    public void setMatchExt2(String matchExt2) {
        this.matchExt2 = matchExt2 == null ? null : matchExt2.trim();
    }

    public String getProvideId() {
        return provideId;
    }

    public void setProvideId(String provideId) {
        this.provideId = provideId == null ? null : provideId.trim();
    }

}