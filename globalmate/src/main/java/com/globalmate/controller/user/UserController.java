package com.globalmate.controller.user;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.user.UserCheckFailException;
import com.globalmate.service.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public JsonResult addUser(@RequestBody User user) {
        try {
            userService.checkUser(user);
        } catch (UserCheckFailException e) {
            return buildFail(e.getMessage());
        }
        user = userService.registerUser(user);
        return buildSuccess(user);
    }

    @GetMapping("login/{phone}/{pwd}")
    public JsonResult login(@PathVariable("phone") String phone,
                            @PathVariable("pwd") String pwd) {
        User user = userService.checkPwd(phone, pwd);
        if (user == null) {
            return buildFail("手机号或密码有误，请重新输入！");
        }

        String token = userService.generateToken();
        userService.putUserToken(token, user) ;
        return buildSuccess(token);
    }


    @GetMapping("delete")
    public JsonResult delete(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        int delete = userService.falseDelete(user);
        if (delete == 1) {
            return buildSuccess();
        }
        return buildFail("删除用户失败!");
    }

    @PutMapping("update")
    public JsonResult update(@RequestBody User user) {
        user = userService.updateUser(user);
        if (user != null) {
            return buildSuccess(user);
        }
        return buildFail("更新用户信息失败！");
    }

    @GetMapping("listSchool")
    public JsonResult listSchool() {
        return buildSuccess(userService.listSchool());
    }

    @GetMapping("list")
    public JsonResult list(boolean bySchool, boolean byTag){
        List<User> users = userService.listUsers();
        if (CollectionUtils.isEmpty(users)) {
            return buildSuccess();
        }
        if (bySchool) {
            return buildSuccess(users.stream().collect(Collectors.groupingBy(User::getSchool)));
        }
        if (byTag) {
            return buildSuccess(users.stream().collect(Collectors.groupingBy(User::getUserTag)));
        }
        return buildSuccess(users);
    }

    @GetMapping("list/{id}")
    public JsonResult list(@PathVariable("id") String id) {
        return buildSuccess(userService.getUser(id));
    }

}
