package com.globalmate.controller.user;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.user.UserCheckFailException;
import com.globalmate.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("\n接收到来自微信服务器的认证消息：[{}, {}]",phone,pwd);
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

}
