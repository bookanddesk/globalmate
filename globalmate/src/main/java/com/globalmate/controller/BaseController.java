package com.globalmate.controller;

import com.github.pagehelper.PageInfo;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.GMIllegalArgumentException;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.PageUtils;
import com.globalmate.uitl.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected JsonResult buildSuccess() {
        return JsonResult.success();
    }

    protected <T> JsonResult buildSuccess(T t) {
        return JsonResult.success(t);
    }

    protected JsonResult buildFail(String msg) {
        return JsonResult.fail(msg);
    }

    protected <T> JsonResult buildFail(String msg, T t) {
        return JsonResult.fail(msg, t);
    }

    protected User getCurrentUser(HttpServletRequest request) {
        return (User) request.getAttribute(GMConstant.USER);
    }

    protected User getCurrentUser() {
        return (User) request.getAttribute(GMConstant.USER);
    }

    protected String getMsg(String msgKey) {
        return getMsg(msgKey, null);
    }

    protected String getMsg(String msgKey, Object[] objects) {
        RequestContext requestContext = new RequestContext(request);
        String message = requestContext.getMessage(msgKey, objects);
        return message != null ? message : msgKey;
    }

    protected void handleValidateError(BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new GMIllegalArgumentException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
    }

    protected Integer getPageNum() {
        Integer pageNum = Optional.ofNullable(request.getParameter(GMConstant.PAGE_NUM))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        return pageNum;
    }

    protected Integer getPageSize() {
        Integer pageSize = Optional.ofNullable(request.getParameter(GMConstant.PAGE_SIZE))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
        return pageSize;
    }

    protected void startPage() {
        PageUtils.startPage(getPageNum(), getPageSize(), true);
    }

    protected <T> ModelAndView buildMV(String viewName, List<T> datas) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        if (datas != null) {
            modelAndView.addObject(GMConstant.PAGE_INFO, new PageInfo<>(datas));
        }
        return modelAndView;
    }

    protected <T, P> ModelAndView buildMV(String viewName, List<T> datas, P p) {
        return buildMV(viewName, datas).addObject(GMConstant.QUERY_PARAM, p);
    }

    protected String getParameter(String param) {
        return request.getParameter(param);
    }

}