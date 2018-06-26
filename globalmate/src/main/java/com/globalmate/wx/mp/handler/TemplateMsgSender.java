package com.globalmate.wx.mp.handler;

import com.globalmate.wx.mp.config.TemplateMsgConfig;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author XingJiajun
 * @Date 2018/6/26 21:17
 * @Description
 */
public class TemplateMsgSender {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String NoLinkTemplateTextColor = "#000000";
    public static final String WithLinkTemplateTextColor = "#2C7EF1";
    private WxMpService wxMpService;

    public TemplateMsgSender(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    public void sendTemplateMsg(TemplateMsgConfig config) {
        String toUserId = config.getMsgUser();
        if (this.log.isDebugEnabled()) {
            this.log.debug("weixin mp send template msg.userId=" + toUserId);
        }

        Map<String, String> tempData = config.getTempData();
        List<WxMpTemplateData> datas = new ArrayList(tempData.size());
        Iterator it = tempData.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            String val = (String)tempData.get(key);
            WxMpTemplateData tmp = new WxMpTemplateData();
            tmp.setColor(StringUtils.isBlank(config.getMsgUrl()) ? "#000000" : "#2C7EF1");
            tmp.setName(key);
            tmp.setValue(val);
            datas.add(tmp);
        }

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage().builder()
        .toUser(toUserId).url(config.getMsgUrl()).templateId(config.getMsgId()).build();
        templateMessage.setData(datas);

        try {
            this.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException var9) {
            this.log.error("send template msg exception.[wxId=" + toUserId + "][msgId=" + config.getMsgId() + "]", var9);
        }

    }
}
