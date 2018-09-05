package com.globalmate.controller.need;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.globalmate.data.entity.NeedCommon;
import com.globalmate.data.entity.vo.NeedAggEntity;
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
        	need=needService.updateNeed(need);
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
        return buildSuccess(needService.associatedQuery(need, searchText));
//        return buildSuccess(needService.queryLike(need));
    }


    @PostMapping("addCommon")
    public JsonResult addCommonNeed(@RequestBody @Validated NeedCommon needCommon, BindingResult result) {
        handleValidateError(result);
        return buildSuccess(needService.addCommonNeed(needCommon, getCurrentUser()));
    }

}
