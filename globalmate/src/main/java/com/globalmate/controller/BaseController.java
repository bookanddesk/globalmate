package com.globalmate.controller;

import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected JsonResult buildSuccess(){
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
        return (User) request.getAttribute("user");
    }

}