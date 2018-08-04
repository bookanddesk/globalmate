package com.globalmate.service.wx;

import com.globalmate.data.entity.po.MsgEntity;
import com.globalmate.uitl.StringUtils;
import com.globalmate.wx.mp.service.WeixinService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/7/20 15:51
 * @Description
 */
public abstract class WxTempMsgSendService implements IMsgSendService {

    @Autowired
    private WeixinService weixinService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public WxMpTemplateMessage buildWxTempMsg(MsgEntity msgEntity) {
        msgEntity.setMsgTempId("VgbeYJT2h1DalNZDs17zRjsgD2M6jKHXZ9U3BUlwCuY");
        checkNotNull(msgEntity);
        checkNotNull(msgEntity.getToUserId());
        checkNotNull(msgEntity.getMsgTempId());
        Map<String, String> data = msgEntity.getData();
        checkNotNull(data);

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(msgEntity.getToUserId())
                .templateId(msgEntity.getMsgTempId())
                .url(msgEntity.getUrl())
                .build();

        List<WxMpTemplateData> dataList = new ArrayList<>(data.size());
        String colour = msgEntity.getColour();
        for (Iterator<Map.Entry<String, String>> iterator = data.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> next = iterator.next();
            if (StringUtils.isNotBlank(next.getKey())) {
                dataList.add(new WxMpTemplateData(next.getKey(), next.getValue(), colour));
            }
        }
        templateMessage.setData(dataList);

        return templateMessage;
    }

    @Override
    public String send(WxMpTemplateMessage message) {
        if (message == null) {
            logger.error("send weixinTemplate msg exception:WxMpTemplateMessage can't be null!");
            return null;
        }
        String msgId = null;
        try {
            msgId =  weixinService.sendTemplateMsg(message);
        } catch (WxErrorException e) {
            logger.error("send weixinTemplate msg exception.[2User=" + message.getToUser()
                    + "][tempId=" + message.getTemplateId() + "]", e);
        }
        return msgId;
    }

    @Override
    public String send(MsgEntity msgEntity) {
        return send(buildWxTempMsg(msgEntity));
    }

    abstract String getMsgTemplateId(MsgEntity msgEntity);
}
