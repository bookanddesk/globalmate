package com.globalmate.controller.wx;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.GMConstant;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XingJiajun
 * @Date 2018/8/1 16:22
 * @Description
 */
@RestController
@RequestMapping("/wechat/oauth")
public class WxOAuthController extends BaseController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private UserService userService;

    @GetMapping("oauthUrl")
    public JsonResult buildOAuthUrl(String redirect) {
        return buildSuccess(wxMpService.oauth2buildAuthorizationUrl(
                redirect,
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, null));
    }

    @GetMapping("oauthCb")
    public JsonResult getOAuthUser(String code) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        if (wxMpOAuth2AccessToken != null) {
            List<User> users = userService.selectWXUser(wxMpOAuth2AccessToken.getOpenId());
            if (CollectionUtils.isNotEmpty(users)) {
                User user = users.get(0);
                String token = userService.putUserToken(null, user);
                Map<String, Object> map = new HashMap<>(2);
                map.put(GMConstant.TOKEN, token);
                map.put(GMConstant.USER, user);
                return buildSuccess(map);
            }
        }
        return buildFail("oauth 2 wx failed !");
    }



}
