package com.globalmate.common;

import com.globalmate.common.context.InvocationInfoProxy;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author XingJiajun
 * @Date 2018/7/9 18:37
 * @Description
 */
public class I18nInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        Locale locale = request.getLocale();
        RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);
        InvocationInfoProxy.reset();
        InvocationInfoProxy.setLocalStr(locale.getLanguage());
        return super.preHandle(request, response, handler);
    }

}
