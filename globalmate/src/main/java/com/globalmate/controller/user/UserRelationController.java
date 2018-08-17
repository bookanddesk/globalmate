package com.globalmate.controller.user;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.UFansRelations;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.user.UserRelationException;
import com.globalmate.service.user.RelationService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XingJiajun
 * @Date 2018/8/17 9:47
 * @Description
 */
@RestController
@RequestMapping("userRelation")
public class UserRelationController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RelationService relationService;

    /**
     * 添加好友
     * @param targetUserId 目标用户id
     * @return
     */
    @GetMapping("addFriend")
    public JsonResult addFriend(String targetUserId) {
        if (StringUtils.isBlank(targetUserId)) {
            return buildFail("targetUserId can't be blank!");
        }
        User targetUser = userService.getUser(targetUserId);
        if (targetUser == null) {
            return buildFail("can't fin user with targetUserId : [" + targetUserId + "]");
        }
        UFansRelations relations;
        try {
            relations = relationService.addFriend(targetUser);
        } catch (UserRelationException e) {
            return buildFail(e.getMessage());
        }
        if (relations != null) {
            return buildSuccess(relations);
        }
        return buildFail("addFriend failed with userId : ["
                + getCurrentUser().getId() + "],targetUserId : [" + targetUserId + "]");
    }

}
