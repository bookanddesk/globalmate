package com.globalmate.data.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class UEvaluation {
    private String id;

    private String uEvaluatorId;

    private String uEvluatorName;

    @NotBlank(message = "评论对象id不能为空")
    private String uTargeterId;

    private String uTargeterName;

    private String needId;

    @NotBlank(message = "评分不能为空")
    private String score;

    private String content;

    private Date modifyTime;

    private Date createTime;

    private String evaExt1;

    private String evaExt2;

    private String evaExt3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getuEvaluatorId() {
        return uEvaluatorId;
    }

    public void setuEvaluatorId(String uEvaluatorId) {
        this.uEvaluatorId = uEvaluatorId == null ? null : uEvaluatorId.trim();
    }

    public String getuEvluatorName() {
        return uEvluatorName;
    }

    public void setuEvluatorName(String uEvluatorName) {
        this.uEvluatorName = uEvluatorName == null ? null : uEvluatorName.trim();
    }

    public String getuTargeterId() {
        return uTargeterId;
    }

    public void setuTargeterId(String uTargeterId) {
        this.uTargeterId = uTargeterId == null ? null : uTargeterId.trim();
    }

    public String getuTargeterName() {
        return uTargeterName;
    }

    public void setuTargeterName(String uTargeterName) {
        this.uTargeterName = uTargeterName == null ? null : uTargeterName.trim();
    }

    public String getNeedId() {
        return needId;
    }

    public void setNeedId(String needId) {
        this.needId = needId == null ? null : needId.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvaExt1() {
        return evaExt1;
    }

    public void setEvaExt1(String evaExt1) {
        this.evaExt1 = evaExt1 == null ? null : evaExt1.trim();
    }

    public String getEvaExt2() {
        return evaExt2;
    }

    public void setEvaExt2(String evaExt2) {
        this.evaExt2 = evaExt2 == null ? null : evaExt2.trim();
    }

    public String getEvaExt3() {
        return evaExt3;
    }

    public void setEvaExt3(String evaExt3) {
        this.evaExt3 = evaExt3 == null ? null : evaExt3.trim();
    }
}