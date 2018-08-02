package com.globalmate.controller.basic;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.UEvaluation;
import com.globalmate.data.entity.User;
import com.globalmate.service.certify.UCertifyInfoService;
import com.globalmate.service.evaluate.EvaluateService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.GMConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/26 17:00
 * @Description
 */
@Controller
@RequestMapping({"basicData", ""})
public class BasicDataController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private UCertifyInfoService ucertifyInfoService;

    @GetMapping("userQuery")
    public ModelAndView userQuery(User user) {
        startPage();
        List<User> users = userService.listUsersLike(user);
        return buildMV(GMConstant.USER_PAGE, users, user);
    }

    @GetMapping("evaluateQuery")
    public ModelAndView evaluateQuery(UEvaluation evaluation) {
        startPage();
        List<UEvaluation> uEvaluations = evaluateService.listEvaluation(evaluation);
        return buildMV(GMConstant.EVLUATION_PAGE, uEvaluations, evaluation);
    }
    
    @GetMapping("cetifiyQuery")
    public ModelAndView cetifiyQuery(UCertifyInfo certifyInfo) {
        startPage();
        List<UCertifyInfo> ucertifyInfos = ucertifyInfoService.listCertifyInfo(certifyInfo);
        return buildMV(GMConstant.CETIFIY_PAGE, ucertifyInfos, certifyInfo);
    }
    
    @GetMapping("cetifiyUpdate")
    public void cetifiyUpdate(UCertifyInfo certifyInfo) {
        int isEffective = certifyInfo.getIsEffective();
        UCertifyInfo ucertifyInfo = ucertifyInfoService.getUCertifyInfo(certifyInfo.getId());
        if(ucertifyInfo!=null){
        	ucertifyInfo.setIsEffective(isEffective);
        	ucertifyInfoService.updateUCertifyInfo(ucertifyInfo);
        }
    }

    @GetMapping({"", "login", "index"})
    public ModelAndView login() {
        return buildMV(GMConstant.LOGIN_PAGE, null);
    }

    @GetMapping("logout")
    public ModelAndView logout() {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return buildMV(GMConstant.LOGIN_PAGE, null);
    }

    @GetMapping("home")
    public ModelAndView home() {
        return buildMV(GMConstant.HOME_PAGE, null);
    }


}
