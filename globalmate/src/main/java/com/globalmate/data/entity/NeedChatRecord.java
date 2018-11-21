package com.globalmate.data.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

public class NeedChatRecord implements Serializable {

    public NeedChatRecord() {
    }

    private String id;

    @NotBlank
    private String needId;

    private String uNeedId;

    private String uNeedName;

    private String imChatId;

    private String uChatTargetId;

    private String uChatTargetName;

    private Date imChatCreateTime;

    private Date imChatModifyTime;

    private String ext1;

    private String ext2;

    private String ext3;

    private NeedChatRecord(Builder builder) {
        setId(builder.id);
        setNeedId(builder.needId);
        setuNeedId(builder.uNeedId);
        setuNeedName(builder.uNeedName);
        setImChatId(builder.imChatId);
        setuChatTargetId(builder.uChatTargetId);
        setuChatTargetName(builder.uChatTargetName);
        setImChatCreateTime(builder.imChatCreateTime);
        setImChatModifyTime(builder.imChatModifyTime);
        setExt1(builder.ext1);
        setExt2(builder.ext2);
        setExt3(builder.ext3);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public String getImChatId() {
        return imChatId;
    }

    public void setImChatId(String imChatId) {
        this.imChatId = imChatId == null ? null : imChatId.trim();
    }

    public String getuChatTargetId() {
        return uChatTargetId;
    }

    public void setuChatTargetId(String uChatTargetId) {
        this.uChatTargetId = uChatTargetId == null ? null : uChatTargetId.trim();
    }

    public String getuChatTargetName() {
        return uChatTargetName;
    }

    public void setuChatTargetName(String uChatTargetName) {
        this.uChatTargetName = uChatTargetName == null ? null : uChatTargetName.trim();
    }

    public Date getImChatCreateTime() {
        return imChatCreateTime;
    }

    public void setImChatCreateTime(Date imChatCreateTime) {
        this.imChatCreateTime = imChatCreateTime;
    }

    public Date getImChatModifyTime() {
        return imChatModifyTime;
    }

    public void setImChatModifyTime(Date imChatModifyTime) {
        this.imChatModifyTime = imChatModifyTime;
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

    public static final class Builder {
        private String id;
        private String needId;
        private String uNeedId;
        private String uNeedName;
        private String imChatId;
        private String uChatTargetId;
        private String uChatTargetName;
        private Date imChatCreateTime;
        private Date imChatModifyTime;
        private String ext1;
        private String ext2;
        private String ext3;

        private Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setNeedId(String needId) {
            this.needId = needId;
            return this;
        }

        public Builder setUNeedId(String uNeedId) {
            this.uNeedId = uNeedId;
            return this;
        }

        public Builder setUNeedName(String uNeedName) {
            this.uNeedName = uNeedName;
            return this;
        }

        public Builder setImChatId(String imChatId) {
            this.imChatId = imChatId;
            return this;
        }

        public Builder setUChatTargetId(String uChatTargetId) {
            this.uChatTargetId = uChatTargetId;
            return this;
        }

        public Builder setUChatTargetName(String uChatTargetName) {
            this.uChatTargetName = uChatTargetName;
            return this;
        }

        public Builder setImChatCreateTime(Date imChatCreateTime) {
            this.imChatCreateTime = imChatCreateTime;
            return this;
        }

        public Builder setImChatModifyTime(Date imChatModifyTime) {
            this.imChatModifyTime = imChatModifyTime;
            return this;
        }

        public Builder setExt1(String ext1) {
            this.ext1 = ext1;
            return this;
        }

        public Builder setExt2(String ext2) {
            this.ext2 = ext2;
            return this;
        }

        public Builder setExt3(String ext3) {
            this.ext3 = ext3;
            return this;
        }

        public NeedChatRecord build() {
            return new NeedChatRecord(this);
        }
    }
}