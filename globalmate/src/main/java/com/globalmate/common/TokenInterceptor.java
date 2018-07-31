package com.globalmate.common;

import com.globalmate.common.context.InvocationInfoProxy;
import com.globalmate.data.entity.User;
import com.globalmate.exception.user.UserTokenFailException;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.GMConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(GMConstant.TOKEN) != null ? request.getHeader(GMConstant.TOKEN) :
                request.getParameter(GMConstant.TOKEN) != null ? request.getParameter(GMConstant.TOKEN) :
                        (String) request.getSession().getAttribute(GMConstant.TOKEN);

        if (StringUtils.isBlank(token)) {
            throw new UserTokenFailException("token不能为空！");
        }

        User user = userService.getTokenUser(token);
        if (user == null) {
            throw new UserTokenFailException("token失效！");
        }

        request.setAttribute("user", user);
        response.setHeader("phone", user.getPhone());
        response.setHeader("nickName", user.getNikename());

        userService.putUserToken(token, user);
        InvocationInfoProxy.setUserId(user.getId());

        return super.preHandle(request, response, handler);
    }
}
