package com.globalmate.controller.need;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.need.NeedService;

@RestController
@RequestMapping("need")
public class NeedController extends BaseController {
    @Autowired
    private NeedService needService;
   
    @PostMapping("update")
    public JsonResult addNeed(@RequestBody Need need) {
        try {
        	need=needService.updateNeed(need);
        } catch (Exception e) {
            return buildFail(e.getMessage());
        }
        return buildSuccess(need);
    }

}
