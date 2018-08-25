package com.globalmate.controller.Evaluate;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.UEvaluation;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.data.entity.vo.EvaluationAggEntity;
import com.globalmate.service.evaluate.EvaluateService;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/17 15:25
 * @Description
 */
@RestController
@RequestMapping("evaluate")
public class EvaluateController extends BaseController {

    @Autowired
    private EvaluateService evaluateService;

    @PostMapping("add")
    public JsonResult add(@RequestBody @Validated UEvaluation evaluation,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        handleValidateError(bindingResult);
        if(StringUtils.isBlank(evaluation.getNeedId())) {
            return buildFail("needId can't be blank when evaluate others!");
        }
        evaluation = evaluateService.addEvaluation(getCurrentUser(request), evaluation);
        return buildSuccess(evaluation);
    }

    @PostMapping("add/sys")
    public JsonResult addSysEvaluation(@RequestBody UEvaluation evaluation, HttpServletRequest request) {
        evaluation = evaluateService.addSysEvaluation(getCurrentUser(request), evaluation);
        return buildSuccess(evaluation);
    }

    @PutMapping("update")
    public JsonResult update(@RequestBody @Validated UEvaluation evaluation,
                             BindingResult bindingResult,
                             HttpServletRequest request) {
        handleValidateError(bindingResult);
        evaluation = evaluateService.updateEvaluation(getCurrentUser(request), evaluation);
        return buildSuccess(evaluation);
    }
    
    @GetMapping("list")
    public JsonResult list(HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "false") Boolean onlyCurrentUser,
                           @RequestParam(required = false, defaultValue = "false") Boolean acquired) {
        List<EvaluationAggEntity> entities = evaluateService
                .listEvaluationAgg(onlyCurrentUser ? getCurrentUser(request) : null, acquired);
        return buildSuccess(entities);
    }



}
