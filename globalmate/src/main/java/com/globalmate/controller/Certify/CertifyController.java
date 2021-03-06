package com.globalmate.controller.Certify;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.certify.UCertifyInfoService;
import com.globalmate.uitl.EmailUtils;
import com.globalmate.uitl.RegexUtils;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    
    @PostMapping("addList")
    public JsonResult addList(@RequestBody @Validated UCertifyInfo[] ucertifyInfoList,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        handleValidateError(bindingResult);
        List<UCertifyInfo> newUcertifyInfoList = new ArrayList<UCertifyInfo>();
        if(ucertifyInfoList !=null && ucertifyInfoList.length>0){
	        for(int i=0;i<ucertifyInfoList.length;i++){
	        	UCertifyInfo ucertifyInfo = ucertifyInfoList[i];
	        	if(ucertifyInfo !=null){
	        		ucertifyInfo = ucertifyInfoService.addUCertifyInfo(getCurrentUser(request), ucertifyInfo);
	        		newUcertifyInfoList.add(ucertifyInfo);
	        	}
	        }
        }
        return buildSuccess(newUcertifyInfoList);
    }

    @PutMapping("update")
    public JsonResult update(@RequestBody @Validated UCertifyInfo ucertifyInfo,
                             BindingResult bindingResult,
                             HttpServletRequest request) {
        handleValidateError(bindingResult);
        ucertifyInfo = ucertifyInfoService.updateUCertifyInfo(ucertifyInfo);
        return buildSuccess(ucertifyInfo);
    }

    @PostMapping("updateList")
    public JsonResult updateList(@RequestBody @Validated UCertifyInfo[] ucertifyInfoList,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        handleValidateError(bindingResult);
        List<UCertifyInfo> newUcertifyInfoList = new ArrayList<UCertifyInfo>();
        if(ucertifyInfoList !=null && ucertifyInfoList.length>0){
            for(int i=0;i<ucertifyInfoList.length;i++){
                UCertifyInfo ucertifyInfo = ucertifyInfoList[i];
                if(ucertifyInfo !=null){
                    ucertifyInfo = ucertifyInfoService.updateUCertifyInfo(ucertifyInfo);
                    newUcertifyInfoList.add(ucertifyInfo);
                }
            }
        }
        return buildSuccess(newUcertifyInfoList);
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

    @GetMapping("emailVerify")
    public JsonResult emailVerify(String email) {
        if(StringUtils.isEmpty(email) || !RegexUtils.checkEmail(email)) {
            return JsonResult.fail("emailAddress is illegal!");
        }
        return JsonResult.success(EmailUtils.sendVerificationCode(email));
    }


}
