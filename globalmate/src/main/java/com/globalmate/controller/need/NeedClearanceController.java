package com.globalmate.controller.need;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalmate.controller.BaseController;
import com.globalmate.data.dao.mapper.ClearanceMapper;
import com.globalmate.data.entity.Clearance;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.IdGenerator;

@RestController
@RequestMapping("need/clearance")
public class NeedClearanceController extends BaseController {
    @Autowired
    private NeedService needService;
    @Autowired
    private ClearanceMapper clearanceMapper;
    
    @PostMapping("add")
    public JsonResult addNeed(@RequestBody Clearance clearance,HttpServletRequest request) {
    	//need自己组装,存储基本信息
    	Need need=new Need();
    	need.setCreateTime(new Date());
    	need.setType(NeedTypeEnum.clearance.name());
    	//取当前登录用户
    	User user = getCurrentUser(request);
    	if(user != null)
    		need.setUserId(user.getId());
        try {
        	need=needService.commitNeed(need);
        } catch (Exception e) {
            return buildFail(e.getMessage());
        }
        clearance.setNeedId(need.getId());
        if (StringUtils.isBlank(clearance.getId())) {
        	clearance.setId(IdGenerator.generateId());
        }
        clearanceMapper.insert(clearance);
           
        return buildSuccess(clearance);
    }

}
