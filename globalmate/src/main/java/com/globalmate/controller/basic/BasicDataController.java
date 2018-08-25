package com.globalmate.controller.basic;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.*;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.assistance.AssistService;
import com.globalmate.service.assistance.IAssistService;
import com.globalmate.service.certify.UCertifyInfoService;
import com.globalmate.service.evaluate.EvaluateService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.service.user.SchoolService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.GMConstant;

import com.globalmate.uitl.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private NeedService needService;
    @Autowired
    private IAssistService assistService;


    @GetMapping("userQuery")
    public ModelAndView userQuery(User user, String uExt1_2) {
        startPage();
        List<User> users = userService.queryByLoginTime(user, uExt1_2);
        ModelAndView modelAndView = buildMV(GMConstant.USER_PAGE, users, user);
        modelAndView.addObject("uExt1_2", uExt1_2);
        return modelAndView;
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

    @GetMapping("needQuery")
    public ModelAndView needQuery(Need need, @Param("createTime2") Date createTime2) {
        startPage();
        List<Need> needs = needService.queryNeedLike(need);
        if (createTime2 != null) {
            needs = needs.stream().filter(x -> x.getCreateTime().before(createTime2)).collect(Collectors.toList());
        }
        needs.forEach(x -> {
            String enable = x.getEnable();
            x.setEnable(Optional.ofNullable(GMEnums.NeedStatus.transformCode(Integer.parseInt(enable)).getValue())
                    .orElse(enable));
            String type = x.getType();
            x.setType(NeedTypeEnum.valueOf(type).getShowValue());
        });

        ModelAndView modelAndView = buildMV(GMConstant.NEED_PAGE, needs, need);

        GMEnums.NeedStatus[] values = GMEnums.NeedStatus.values();
        List<Map<String, Object>> statusOptions = new ArrayList<>(values.length);
        for (GMEnums.NeedStatus needStatus : values) {
            Map<String, Object> option = new HashMap<>(3, 1);
            option.put("text", needStatus.getValue());
            option.put("value", needStatus.getCode());
            option.put("selected", String.valueOf(needStatus.getCode()).equals(String.valueOf(need.getEnable())));
            statusOptions.add(option);
        }
        modelAndView.addObject("statusOptions", statusOptions);

        NeedTypeEnum[] needTypeEnums = NeedTypeEnum.values();
        List<Map<String, Object>> typeOptions = new ArrayList<>(needTypeEnums.length);
        for (NeedTypeEnum typeEnum : needTypeEnums) {
            Map<String, Object> option = new HashMap<>(3, 1);
            option.put("text", typeEnum.getShowValue());
            option.put("value", typeEnum.name());
            option.put("selected", StringUtils.equals(need.getType(), typeEnum.name()));
            typeOptions.add(option);
        }

        modelAndView.addObject("typeOptions", typeOptions);

        modelAndView.addObject("createTime2", createTime2);

        return modelAndView;
    }


    @GetMapping("dealQuery")
    public ModelAndView dealQuery(SysAssistanceDeal deal, @Param("assistCreateTime2")Date assistCreateTime2) {
        startPage();
        List<SysAssistanceDeal> deals = assistService.queryLike(deal);

        if (assistCreateTime2 != null) {
            deals = deals.stream().filter(x -> x.getAssistCreateTime().before(assistCreateTime2)).collect(Collectors.toList());
        }

        deals.forEach(x -> x.setAssistStatus(GMEnums.AssistAction.valueOf(x.getAssistStatus().toUpperCase()).getText()));

        ModelAndView modelAndView = buildMV(GMConstant.DEAL_PAGE, deals, deal);

        GMEnums.AssistAction[] values = GMEnums.AssistAction.values();
        List<Map<String, Object>> actionOptions = new ArrayList<>(values.length);
        for (GMEnums.AssistAction assistAction : values) {
            Map<String, Object> option = new HashMap<>(3, 1);
            option.put("text", assistAction.getText());
            option.put("value", assistAction.getValue());
            option.put("selected", StringUtils.equals(deal.getAssistStatus(), assistAction.getValue()));
            actionOptions.add(option);
        }
        modelAndView.addObject("actionOptions", actionOptions);

        modelAndView.addObject("assistCreateTime2", assistCreateTime2);

        return modelAndView;
    }

}
