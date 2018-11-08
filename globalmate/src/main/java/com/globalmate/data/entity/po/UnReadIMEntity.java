package com.globalmate.data.entity.po;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author XingJiajun
 * @Date 2018/11/5 20:52
 * @Description
 */
public class UnReadIMEntity {

    private String id;
    private String fromUserId;
    @NotBlank
    private String toUserId;
    private String needId;
    private String msgInfo;
    private Integer msgCount;
    private Date msgSendDate;
    private String toChartId;
    private boolean fromDetail = true;


    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getNeedId() {
        return needId;
    }

    public void setNeedId(String needId) {
        this.needId = needId;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public Integer getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Integer msgCount) {
        this.msgCount = msgCount;
    }

    public Date getMsgSendDate() {
        return msgSendDate;
    }

    public void setMsgSendDate(Date msgSendDate) {
        this.msgSendDate = msgSendDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToChartId() {
        return toChartId;
    }

    public void setToChartId(String toChartId) {
        this.toChartId = toChartId;
    }

    public boolean isFromDetail() {
        return fromDetail;
    }

    public void setFromDetail(boolean fromDetail) {
        this.fromDetail = fromDetail;
    }
}
