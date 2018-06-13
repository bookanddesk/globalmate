package com.globalmate.controller;

import com.globalmate.data.entity.po.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class BaseController {


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

}