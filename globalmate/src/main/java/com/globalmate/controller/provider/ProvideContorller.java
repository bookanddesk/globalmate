package com.globalmate.controller.provider;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.TProvide;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.provider.ProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 可提供的服务
 */

@RestController
@RequestMapping("provide")
public class ProvideContorller extends BaseController {

    @Autowired
    private ProvideService provideService;

    @PostMapping("add")
    public JsonResult addUser(HttpServletRequest request, @RequestBody TProvide provide) {
        provide = provideService.addProvide(getCurrentUser(request), provide);
        if (provide != null) {
            return buildSuccess(provide);
        }
        return buildFail("添加帮助失败！");
    }

    @PutMapping("update")
    public JsonResult update(HttpServletRequest request, @RequestBody TProvide provide) {
        provide = provideService.updateProvide(getCurrentUser(request), provide);
        if (provide != null) {
            return buildSuccess(provide);
        }
        return buildFail("更新帮助信息失败！");
    }

    @GetMapping("list")
    public JsonResult update(HttpServletRequest request,
                             @RequestParam(required = false, defaultValue = "false") Boolean onlyCurrentUser) {
        List<TProvide> provides = provideService.getProvide(onlyCurrentUser ? getCurrentUser(request) : null);
        return buildSuccess(provides);
    }


}
