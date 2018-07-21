package com.globalmate.service.wx;

import com.globalmate.data.entity.po.MsgEntity;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @author XingJiajun
 * @Date 2018/7/20 15:49
 * @Description
 */
public interface IMsgSendService {

    WxMpTemplateMessage buildWxTempMsg(MsgEntity msgEntity);

    String send(WxMpTemplateMessage message);

    String send(MsgEntity msgEntity);

}
