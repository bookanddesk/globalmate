package com.globalmate.controller.user;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.Location;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.user.UserCheckFailException;
import com.globalmate.service.location.LocationService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.StringUtils;
import com.google.common.primitives.Ints;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

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
        request.getSession().setAttribute(GMConstant.TOKEN, token);
        request.getSession().setAttribute(GMConstant.USER, user);
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
    public JsonResult list(boolean bySchool, boolean byTag, boolean byNice){
        List<User> users = userService.listAllUsers();
        if (CollectionUtils.isEmpty(users)) {
            return buildSuccess();
        }
        if (byNice) {
            users.sort((x, y) -> Ints.compare(Optional.ofNullable(y.getNice()).orElse(0),
                    Optional.ofNullable(x.getNice()).orElse(0)));
            return buildSuccess(users);
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

    @GetMapping("getUserByToken")
    public JsonResult token(HttpServletRequest request) {
        User user = Optional.ofNullable(getCurrentUser(request))
                .map(x->userService.getUser(x.getId()))
                .orElse(null);
        return buildSuccess(user);
    }

    @GetMapping("getToken")
    public JsonResult getToken (String userId, String openId) {
        User user = userService.getUser(userId);
        if (user == null) {
            List<User> users = userService.selectWXUser(openId);
            if (CollectionUtils.isNotEmpty(users)) {
                user = users.get(0);
            }
        }
        if (user != null) {
            userService.updateLoginTime(user);
            return buildSuccess(userService.putUserToken(null, user));
        }
        return buildFail("can't find user with userid["
                + userId + "] and openid[" + openId + "]");
    }

//    @GetMapping("location")
    public JsonResult locationInit() throws DocumentException {
        locationService.resolveLocation(null);
        return buildSuccess();
    }


    @GetMapping("country")
    public JsonResult country(Boolean isEN) {
        return buildSuccess(locationService.getCountries(Optional.ofNullable(isEN).orElse(false)));
    }

    @GetMapping("countryWithInitials")
    public JsonResult countryWithInitials(Boolean isEN) {
        List<Location> countries = locationService.getCountriesWithInitials(Optional.ofNullable(isEN).orElse(false));
        return buildSuccess(countries
                .stream()
                .collect(Collectors.groupingBy(Location::getCountryInitials)));
    }

    @GetMapping("city")
    public JsonResult city(Boolean isEN, Location location) {
        return buildSuccess(locationService.getLocations(Optional.ofNullable(isEN).orElse(false), location));
    }

    @GetMapping("cityWithInitials")
    public JsonResult cityWithInitials(Boolean isEN, Location location) {
        List<Location> cities = locationService.getCitiesWithInitials(Optional.ofNullable(isEN).orElse(false), location);
        return buildSuccess(cities
                .stream()
                .collect(Collectors.groupingBy(Location::getCityInitials)));
    }



}
