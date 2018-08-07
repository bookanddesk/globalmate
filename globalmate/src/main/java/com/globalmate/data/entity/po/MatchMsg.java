package com.globalmate.data.entity.po;

import com.globalmate.common.context.SpringContextUtils;
import com.globalmate.service.wx.MsgTemplateService;
import com.globalmate.uitl.GMConstant;

/**
 * @author XingJiajun
 * @Date 2018/7/20 15:25
 * @Description
 */
public class MatchMsg extends MsgEntity {

    public MatchMsg() {
        super.setFirst(GMConstant.MATCH_MSG_TEMP_FIRST);
        super.setKeyword1(GMConstant.MATCH_MSG_TEMP_KEYWORD1);
        super.setKeyword2(GMConstant.MATCH_MSG_TEMP_KEYWORD2);
        super.setRemark(GMConstant.MATCH_MSG_TEMP_REMARK);
    }

    @Override
    void setMsgType(String msgType) {
        super.msgType = GMEnums.WXMsgType.MATCH.name();
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        getData().put(getKeyword1(), keyword1);
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        getData().put(getKeyword2(), keyword2);
    }

    @Override
    public void setFirst(String first) {
        getData().put(getFirst(), first);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        getData().put(getRemark(), remark);
    }
}
