package com.globalmate.controller.need;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalmate.controller.BaseController;
import com.globalmate.data.dao.mapper.BuyMapper;
import com.globalmate.data.entity.Buy;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.IdGenerator;

@RestController
@RequestMapping("need/buy")
public class NeedBuyController extends BaseController {
    @Autowired
    private NeedService needService;
    @Autowired
    private BuyMapper buyMapper;
    
    @PostMapping("add")
    public JsonResult addNeed(@RequestBody Buy buy,HttpServletRequest request) {
    	//need自己组装,存储基本信息
    	Need need=new Need();
    	need.setCreateTime(new Date());
    	need.setType(NeedTypeEnum.buy.name());
    	//取当前登录用户
    	User user = getCurrentUser(request);
    	if(user != null)
    		need.setUserId(user.getId());
        try {
        	need=needService.commitNeed(need);
        } catch (Exception e) {
            return buildFail(e.getMessage());
        }
        buy.setNeedId(need.getId());
        if (StringUtils.isBlank(buy.getId())) {
        	buy.setId(IdGenerator.generateId());
        }
        buyMapper.insert(buy);
           
        return buildSuccess(buy);
    }

}
