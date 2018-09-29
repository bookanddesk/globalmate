package com.globalmate.service.wx.handler;

import java.util.Map;

import com.globalmate.service.user.UserService;
import com.globalmate.uitl.GMConstant;
import com.globalmate.service.wx.builder.TextBuilder;
import com.globalmate.service.wx.WeixinService;
import com.globalmate.uitl.StringUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Component
public class SubscribeHandler extends AbstractHandler {

  @Autowired
  private UserService userService;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    WeixinService weixinService = (WeixinService) wxMpService;

    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

    String welcomeText = GMConstant.SUBSCRIBE_MSG_TEMP_INFO;
    if (userWxInfo != null) {
      userService.handleWxUser(userWxInfo);
      if (!StringUtils.equalsIgnoreCase(GMConstant.LANGUAGE_CN, userWxInfo.getLanguage())) {
        welcomeText = GMConstant.SUBSCRIBE_MSG_TEMP_INFO_EN;
      }
    }

//    WxMpXmlOutMessage responseResult = null;
//    try {
//      responseResult = handleSpecial(wxMessage);
//    } catch (Exception e) {
//      this.logger.error(e.getMessage(), e);
//    }
//
//    if (responseResult != null) {
//      return responseResult;
//    }

    try {
      return new TextBuilder().build(welcomeText, wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
    //TODO
    return null;
  }

}
