package com.globalmate.controller.msg;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.NeedChatRecord;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.msg.IMService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author XingJiajun
 * @Date 2018/11/21 14:33
 * @Description
 */
@RestController
@RequestMapping("im")
public class IMController extends BaseController {

    @Autowired
    private IMService imService;
    @Autowired
    private UserService userService;

    @PostMapping("addChatRecord")
    public JsonResult addChatRecord(@RequestBody NeedChatRecord record,
                                    BindingResult result) {
        handleValidateError(result);
        User user = getCurrentUser();
        if (StringUtils.isEmpty(record.getuNeedId())) {
            record.setuNeedId(user.getId());
        }
        if (StringUtils.isEmpty(record.getuNeedName())) {
            record.setuNeedName(Optional.ofNullable(user.getName()).orElse(user.getNikename()));
        }
        if (StringUtils.isEmpty(record.getuChatTargetName())) {
            record.setuChatTargetName(userService.getName(record.getuChatTargetId()));
        }
        return buildSuccess(imService.addChatRecord(record));
    }

    @GetMapping("listChatRecordsByNeedId/{needId}")
    public JsonResult listChatRecordsByNeedId(@PathVariable String needId) {
        return buildSuccess(imService.getChatRecord(NeedChatRecord.newBuilder().setNeedId(needId).build()));
    }

}
