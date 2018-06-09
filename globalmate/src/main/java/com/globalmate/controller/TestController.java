package com.globalmate.controller;

import com.globalmate.uitl.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController{

    @RequestMapping(value = "/test1")
    public Object test1(String s){
        System.out.println(s);
        return JsonResult.fail("test");
    }

}