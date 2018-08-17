package com.globalmate.controller.basic;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.SysSchool;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.UEvaluation;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.certify.UCertifyInfoService;
import com.globalmate.service.evaluate.EvaluateService;
import com.globalmate.service.user.SchoolService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.GMConstant;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SchoolService schoolService;

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
        if (ucertifyInfo != null) {
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

    @GetMapping("schoolQuery")
    public ModelAndView schoolQuery(SysSchool school) {
        startPage();
        List<SysSchool> schools = schoolService.getSchools(school);
        return buildMV(GMConstant.SCHOOL_PAGE, schools, school);
    }

    @GetMapping(value = "exportSchool")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        schoolService.exportExcel(request, response);
    }

    @PostMapping(value = "importSchool")
    public ModelAndView importExcel(MultipartFile file) {
        Workbook workbook = schoolService.generateWorkbook(file);
        if (workbook != null) {
            schoolService.importExcel(workbook);
        }
        startPage();
        List<SysSchool> schools = schoolService.getSchools(null);
        return buildMV(GMConstant.SCHOOL_PAGE, schools, null);
    }

}
