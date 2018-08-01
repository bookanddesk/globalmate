package com.globalmate.controller.need;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalmate.controller.BaseController;
import com.globalmate.data.dao.mapper.NeedOtherMapper;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.NeedOther;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.IdGenerator;

@RestController
@RequestMapping("need/other")
public class NeedOtherController extends BaseController {
	@Autowired
    private NeedService needService;
    @Autowired
    private NeedOtherMapper needOtherMapper;
    
    @PostMapping("add")
    public JsonResult addNeed(@RequestBody NeedOther needOther,HttpServletRequest request) {

    	//need自己组装,存储基本信息
    	Need need=new Need();
    	need.setCreateTime(new Date());
    	need.setType(NeedTypeEnum.learn_cooperation.name());
    	need.setWhere(needOther.getDeparture());
    	//取当前登录用户
    	User user = getCurrentUser(request);
        if(user != null) {
            need.setUserId(user.getId());
            need.setUserName(Optional.ofNullable(user.getNikename()).orElse(user.getName()));
        }
        try {
        	need=needService.commitNeed(need);
        } catch (Exception e) {
            return buildFail(e.getMessage());
        }
        needOther.setNeedId(need.getId());
        if (StringUtils.isBlank(needOther.getId())) {
        	needOther.setId(IdGenerator.generateId());
        }
        needOtherMapper.insert(needOther);
           
        return buildSuccess(needOther);
    }

}
