package com.globalmate.wx.mp.config;

import java.util.Map;

/**
 * @author XingJiajun
 * @Date 2018/6/26 21:16
 * @Description
 */
public class TemplateMsgConfig {
    private String msgId;
    private String msgUrl;
    private String msgUser;
    private Map<String, String> tempData;

    public TemplateMsgConfig() {
    }

    public TemplateMsgConfig(String msgId, String msgUrl, String msgUser, Map<String, String> tempData) {
        this.msgId = msgId;
        this.msgUrl = msgUrl;
        this.msgUser = msgUser;
        this.tempData = tempData;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgUrl() {
        return this.msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public String getMsgUser() {
        return this.msgUser;
    }

    public void setMsgUser(String msgUser) {
        this.msgUser = msgUser;
    }

    public Map<String, String> getTempData() {
        return this.tempData;
    }

    public void setTempData(Map<String, String> tempData) {
        this.tempData = tempData;
    }
}
