package com.globalmate.service.wx;

import javax.annotation.PostConstruct;

import com.globalmate.service.wx.config.WxMpConfig;
import com.globalmate.service.wx.handler.*;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import static me.chanjar.weixin.common.api.WxConsts.*;

@Service
public class WeixinService extends WxMpServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected LogHandler logHandler;

  @Autowired
  protected NullHandler nullHandler;

  @Autowired
  protected KfSessionHandler kfSessionHandler;

  @Autowired
  protected StoreCheckNotifyHandler storeCheckNotifyHandler;

  @Autowired
  private WxMpConfig wxConfig;

  @Autowired
  private LocationHandler locationHandler;

  @Autowired
  private MenuHandler menuHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired
  private UnsubscribeHandler unsubscribeHandler;

  @Autowired
  private SubscribeHandler subscribeHandler;

  private WxMpMessageRouter router;

  @Value("${homePage}")
  private String homePage;
  @Value("${helpPage}")
  private String helpPage;
  @Value("${personalPage}")
  private String personalPage;
  @Value("${myNeed}")
  private String myNeed;
  @Value("${sysEvaluate}")
  private String sysEvaluate;

  @PostConstruct
  public void init() {
    final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
    config.setAppId(this.wxConfig.getAppid());// 设置微信公众号的appid
    config.setSecret(this.wxConfig.getAppsecret());// 设置微信公众号的app corpSecret
    config.setToken(this.wxConfig.getToken());// 设置微信公众号的token
    config.setAesKey(this.wxConfig.getAesKey());// 设置消息加解密密钥
    super.setWxMpConfigStorage(config);

    this.refreshRouter();
  }

  private void refreshRouter() {
    final WxMpMessageRouter newRouter = new WxMpMessageRouter(this);

    // 记录所有事件的日志
    newRouter.rule().handler(this.logHandler).next();

    // 接收客服会话管理事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
        .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
        .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
        .handler(this.kfSessionHandler).end();
    
    // 门店审核事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(WxMpEventConstants.POI_CHECK_NOTIFY)
      .handler(this.storeCheckNotifyHandler)
      .end();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(MenuButtonType.CLICK).handler(this.getMenuHandler()).end();

    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

    // 关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.SUBSCRIBE).handler(this.getSubscribeHandler())
        .end();

    // 取消关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.UNSUBSCRIBE).handler(this.getUnsubscribeHandler())
        .end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.LOCATION).handler(this.getLocationHandler()).end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(XmlMsgType.LOCATION)
        .handler(this.getLocationHandler()).end();

    // 扫码事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.SCAN).handler(this.getScanHandler()).end();

    // 默认
    newRouter.rule().async(false).handler(this.getMsgHandler()).end();

    this.router = newRouter;
  }


  public WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.router.route(message);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  public boolean hasKefuOnline() {
    try {
      WxMpKfOnlineList kfOnlineList = this.getKefuService().kfOnlineList();
      return kfOnlineList != null && kfOnlineList.getKfOnlineList().size() > 0;
    } catch (Exception e) {
      this.logger.error("获取客服在线状态异常: " + e.getMessage(), e);
    }

    return false;
  }

  protected MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unsubscribeHandler;
  }

  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  protected AbstractHandler getScanHandler() {
    return null;
  }

  public String sendTemplateMsg(WxMpTemplateMessage message) throws WxErrorException {
    return getTemplateMsgService().sendTemplateMsg(message);
  }

  public String menuCreate() throws WxErrorException {
    WxMenu menu = new WxMenu();
    WxMenuButton button1 = new WxMenuButton();
    button1.setName("首页Home");
    button1.setType(MenuButtonType.VIEW);
    button1.setUrl(homePage);

    WxMenuButton button2 = new WxMenuButton();
    button2.setType(MenuButtonType.VIEW);
    button2.setName("来帮忙");
    button2.setUrl(helpPage);

    WxMenuButton button3 = new WxMenuButton();
    button3.setName("我的");

    menu.getButtons().add(button1);
//    menu.getButtons().add(button2);
//    menu.getButtons().add(button3);

    WxMenuButton button31 = new WxMenuButton();
    button31.setType(MenuButtonType.VIEW);
    button31.setName("个人中心Me");
    button31.setUrl(personalPage);

    WxMenuButton button32 = new WxMenuButton();
    button32.setType(MenuButtonType.VIEW);
    button32.setName("我的求助");
    button32.setUrl(myNeed);

//    WxMenuButton button33 = new WxMenuButton();
//    button33.setType(MenuButtonType.VIEW);
//    button33.setName("平台反馈");
//    button33.setKey(sysEvaluate);

//    button3.getSubButtons().add(button31);
////    button3.getSubButtons().add(button32);
//        button3.getSubButtons().add(button33);
    menu.getButtons().add(button31);

        return this.getMenuService().menuCreate(menu);
  }

}
