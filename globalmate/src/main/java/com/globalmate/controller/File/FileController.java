package com.globalmate.controller.File;

import com.alibaba.fastjson.JSONObject;
import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.File.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XingJiajun
 * @Date 2018/7/18 16:15
 * @Description
 */
@RestController
@RequestMapping("file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    @GetMapping("/ossPolicy")
    public JsonResult ossPolicy() {
        JSONObject policy = fileService.getOSSPolicy();
        return buildSuccess(policy);
    }

}
