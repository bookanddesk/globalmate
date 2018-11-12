package com.globalmate.controller.need;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.globalmate.data.entity.NeedCommon;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.need.NeedService;

@RestController
@RequestMapping("need")
public class NeedController extends BaseController {
    @Autowired
    private NeedService needService;

    @PostMapping("update")
    public JsonResult addNeed(@RequestBody Need need) {
        try {
            need = needService.updateNeed(need);
        } catch (Exception e) {
            return buildFail(e.getMessage());
        }
        return buildSuccess(need);
    }

    @Deprecated
    @GetMapping("list")
    public JsonResult select(@RequestParam(required = false, defaultValue = "false") Boolean onlyCurrentUser,
                             @RequestParam(required = false) String pageNum) {
        if (StringUtils.isNotBlank(pageNum)) {
            startPage();
        }
        List<NeedAggEntity> needs = needService.getNeedAgg(onlyCurrentUser ? getCurrentUser() : null);
        return buildSuccess(needs);
    }

    @GetMapping("list/{id}")
    public JsonResult selectConceret(@PathVariable("id") String id) {
        return buildSuccess(needService.getNeedAgg(id));
    }

    @GetMapping("query")
    public JsonResult query(Need need, @RequestParam(required = false) String searchText) {
        if (StringUtils.isNotBlank(getParameter(GMConstant.PAGE_NUM))) {
            startPage();
        }
        List<Need> needs = needService.associatedQuery(need, searchText);
        if (CollectionUtils.isNotEmpty(needs)) {
            List<NeedAggEntity> collect = needs.stream()
                    .map(x -> {
                        NeedAggEntity entity = new NeedAggEntity();
                        entity.setNeed(x);
                        entity.setConceretNeed(x.getNeedCommon());
                        entity.getNeed().setNeedCommon(null);
                        return entity;
                    }).collect(Collectors.toList());
            return buildSuccess(collect);
        }
        return buildSuccess(needs);
//        return buildSuccess(needService.queryLike(need));
    }


    @PostMapping("addCommon")
    public JsonResult addCommonNeed(@RequestBody @Validated NeedCommon needCommon, BindingResult result) {
        handleValidateError(result);
        boolean typeIllegal = false;
        try {
            NeedTypeEnum.valueOf(needCommon.getType());
        } catch (IllegalArgumentException e) {
            typeIllegal = true;
        }
        if (typeIllegal) {
            return buildFail("needType illegal!");
        }
        return buildSuccess(needService.addCommonNeed(needCommon, getCurrentUser()));
    }

    @DeleteMapping("delete/{needId}")
    public JsonResult deleteCommonNeed(@PathVariable String needId) {
        Need need = needService.getNeed(needId);
        if (need == null) {
            return buildFail("can't find need with id[" + needId + "]");
        }

        if (!StringUtils.equals(getCurrentUser().getId(), need.getUserId())) {
            return buildFail("Permission denied when delete need with id[" + needId + "]");
        }

        GMEnums.NeedStatus needStatus = Optional.ofNullable(need.getEnable())
                .map(Integer::parseInt)
                .map(GMEnums.NeedStatus::transformCode)
                .orElse(GMEnums.NeedStatus.CLOSE);
        if (needStatus != GMEnums.NeedStatus.OPEN &&
                needStatus != GMEnums.NeedStatus.CLOSE) {
            return buildFail(getMsg("cannotDeleteNeedWithStatus", new Object[]{needStatus.getValue()}));
        }

        return buildSuccess(needService.deleteNeed(needId));
    }

}
