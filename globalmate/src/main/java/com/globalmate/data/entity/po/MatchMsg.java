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
        super.setKeyword3(GMConstant.MATCH_MSG_TEMP_KEYWORD3);
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
        getData().put(getKeyword1(), new MsgValCol(keyword1, getColour()));
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        getData().put(getKeyword2(), new MsgValCol(keyword2, getColour()));
    }

    @Override
    public void setKeyword3(String keyword3) {
        getData().put(getKeyword3(), new MsgValCol(keyword3, getColour()));
    }

    @Override
    public void setFirst(String first) {
        getData().put(getFirst(), new MsgValCol(first, GMConstant.MATCH_MSG_TEMP_FONT_COLOUR_BLACK));
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        getData().put(getRemark(), new MsgValCol(remark, GMConstant.MATCH_MSG_TEMP_FONT_COLOUR_GOLDEN));
    }
}
