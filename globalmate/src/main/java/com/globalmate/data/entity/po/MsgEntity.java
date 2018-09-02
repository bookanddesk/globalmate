package com.globalmate.data.entity.po;

import com.globalmate.uitl.GMConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XingJiajun
 * @Date 2018/7/20 15:18
 * @Description
 */
public abstract class MsgEntity {

    private String toUserId;
    private String fromUserId;
    private String msgTempId;
    private String url;
    private String colour;
    private Date createTime;

    protected String first;
    protected String keyword1;
    protected String keyword2;
    protected String keyword3;
    protected String remark;

    private Map<String, MsgValCol> data;

    public MsgEntity() {
        data = new HashMap<>();
        colour = GMConstant.MATCH_MSG_TEMP_FONT_COLOUR;
    }

    protected String msgType;

    public String getMsgType() {
        return msgType;
    }

    abstract void setMsgType(String msgType);

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMsgTempId() {
        return msgTempId;
    }

    public void setMsgTempId(String msgTempId) {
        this.msgTempId = msgTempId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, MsgValCol> getData() {
        return data;
    }

    protected void setData(Map<String, MsgValCol> data) {
        this.data = data;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public class MsgValCol {
        private String value;
        private String color;

        public MsgValCol(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }


}
