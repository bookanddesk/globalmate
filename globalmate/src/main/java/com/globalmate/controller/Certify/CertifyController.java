package com.globalmate.controller.Certify;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.UEvaluation;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.data.entity.vo.EvaluationAggEntity;
import com.globalmate.service.certify.UCertifyInfoService;
import com.globalmate.service.evaluate.EvaluateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author zhuyjh
 * @Date 2018/8/02 15:25
 * @Description
 */
@RestController
@RequestMapping("certify")
public class CertifyController extends BaseController {

    @Autowired
    private UCertifyInfoService ucertifyInfoService;

    @PostMapping("add")
    public JsonResult add(@RequestBody @Validated UCertifyInfo ucertifyInfo,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        handleValidateError(bindingResult);
        ucertifyInfo = ucertifyInfoService.addUCertifyInfo(getCurrentUser(request), ucertifyInfo);
        return buildSuccess(ucertifyInfo);
    }

    @PutMapping("update")
    public JsonResult update(@RequestBody @Validated UCertifyInfo ucertifyInfo,
                             BindingResult bindingResult,
                             HttpServletRequest request) {
        handleValidateError(bindingResult);
        ucertifyInfo = ucertifyInfoService.updateUCertifyInfo(ucertifyInfo);
        return buildSuccess(ucertifyInfo);
    }
    
    @GetMapping("list")
    public JsonResult list(HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "false") Boolean onlyCurrentUser) {
    	UCertifyInfo certifyInfo = new UCertifyInfo();
    	if(onlyCurrentUser ){
    		User user = getCurrentUser(request);
        	certifyInfo.setuId(user.getId());
    	}
        List<UCertifyInfo> ucertifyInfoList = ucertifyInfoService.listCertifyInfo(certifyInfo);
        return buildSuccess(ucertifyInfoList);
    }



}
