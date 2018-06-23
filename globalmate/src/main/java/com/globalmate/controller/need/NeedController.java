package com.globalmate.controller.need;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalmate.controller.BaseController;
import com.globalmate.data.dao.mapper.AccompanyMapper;
import com.globalmate.data.dao.mapper.BuyMapper;
import com.globalmate.data.dao.mapper.CarryMapper;
import com.globalmate.data.dao.mapper.ClearanceMapper;
import com.globalmate.data.dao.mapper.LearnCooperationMapper;
import com.globalmate.data.entity.Accompany;
import com.globalmate.data.entity.Buy;
import com.globalmate.data.entity.Carry;
import com.globalmate.data.entity.Clearance;
import com.globalmate.data.entity.LearnCooperation;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.NeedWithTypeEntity;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.user.UserCheckFailException;
import com.globalmate.service.need.NeedService;

@RestController
@RequestMapping("need")
public class NeedController extends BaseController {
    @Autowired
    private NeedService needService;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private CarryMapper carryMapper;
    @Autowired
    private AccompanyMapper accompanyMapper;
    @Autowired
    private ClearanceMapper clearanceMapper;
    @Autowired
    private LearnCooperationMapper learnCooperationMapper;
    
    @PostMapping("add")
    public JsonResult addNeed(@RequestBody NeedWithTypeEntity needWithTypeEntity) {
    	Need need = null;
        try {
        	need=needService.commitNeed(needWithTypeEntity.getNeed());
        } catch (Exception e) {
            return buildFail(e.getMessage());
        }
        //判断need.getType()根据不同类型插入不同需求表
        switch(need.getType()){
        case "buy":
        	needWithTypeEntity.getBuy().setNeedId(need.getId());
        	buyMapper.insert(needWithTypeEntity.getBuy());
            break;
        case "carry":
        	needWithTypeEntity.getCarry().setNeedId(need.getId());
        	carryMapper.insert(needWithTypeEntity.getCarry());
            break;
        case "accompany":
        	needWithTypeEntity.getAccompany().setNeedId(need.getId());
        	accompanyMapper.insert(needWithTypeEntity.getAccompany());
            break;
        case "learn_cooperation":
        	needWithTypeEntity.getLearnCooperation().setNeedId(need.getId());
        	learnCooperationMapper.insert(needWithTypeEntity.getLearnCooperation());
            break;
        case "clearance":
        	needWithTypeEntity.getClearance().setNeedId(need.getId());
        	clearanceMapper.insert(needWithTypeEntity.getClearance());
            break;
        default:
        	//...;
            break;
        }
        return buildSuccess(need);
    }

}
