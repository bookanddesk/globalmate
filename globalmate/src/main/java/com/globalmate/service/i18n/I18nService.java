package com.globalmate.service.i18n;

import com.globalmate.common.context.InvocationInfoProxy;
import com.globalmate.common.context.SpringContextUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author XingJiajun
 * @Date 2018/7/9 19:34
 * @Description
 */
public interface I18nService {

    default String getI18nMsg(String key, Object[] objects) {
        String result = null;
        MessageSource messageSource;
        Locale locale;
        try {
            messageSource = (MessageSource) SpringContextUtils.getBean("messageSource");
            String localStr = InvocationInfoProxy.getLocalStr();
            locale = new Locale(localStr);
            result = messageSource.getMessage(key, objects, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == null ? key : result;
    }

    default String getI18nMsg(String key) {
       return getI18nMsg(key, null);
    }

}
