package com.globalmate.controller.user;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.Usergroup;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author XingJiajun
 * @Date 2018/7/17 20:33
 * @Description
 */
@RestController
@RequestMapping("group")
public class UserGroupController extends BaseController {

    @Autowired
    private UserGroupService userGroupService;

    @PostMapping("add")
    public JsonResult add(@RequestBody @Validated Usergroup usergroup,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        handleValidateError(bindingResult);
        usergroup = userGroupService.add(getCurrentUser(request), usergroup);
        return buildSuccess(usergroup);
    }

    @GetMapping("list/{id}")
    public JsonResult list(@PathVariable String id) {
        return buildSuccess(userGroupService.getUserGroup(id));
    }

    @GetMapping("join/{id}")
    public JsonResult join(@PathVariable String id, HttpServletRequest request) {
        return buildSuccess(userGroupService.join(getCurrentUser(request), id));
    }



}
