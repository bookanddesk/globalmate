package com.globalmate.data.entity.po;

import com.globalmate.uitl.GMConstant;

/**
 * @author XingJiajun
 * @Date 2018/10/11 19:27
 * @Description
 */
public class UnreadIMMsg extends MatchMsg {

    private String keyword4 = GMConstant.MATCH_MSG_TEMP_KEYWORD4;

    public void setKeyword4(String keyword4) {
        getData().put(this.keyword4, new MsgValCol(keyword4, getColour()));
    }

    @Override
    void setMsgType(String msgType) {
        super.msgType = GMEnums.WXMsgType.CERTIFY.name();
    }
}
