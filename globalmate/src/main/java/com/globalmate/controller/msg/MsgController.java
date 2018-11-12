package com.globalmate.controller.msg;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.data.entity.po.UnReadIMEntity;
import com.globalmate.service.msg.MsgService;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XingJiajun
 * @Date 2018/11/7 20:30
 * @Description
 */
@RestController
@RequestMapping("msg")
public class MsgController extends BaseController {

    @Autowired
    private MsgService msgService;

    @PostMapping("unreadTempMsg")
    public JsonResult sendUnreadTempMsg(@RequestBody UnReadIMEntity entity, BindingResult result) {
        handleValidateError(result);
        if (StringUtils.isEmpty(entity.getFromUserId())) {
            entity.setFromUserId(getCurrentUser().getId());
        }
        if (msgService.isSendFrequently(entity)) {
            return buildFail(getMsg("reminderMessageIsTooFrequent"));
        }
        msgService.sendUnreadIMTempMsg(entity);
        return buildSuccess();
    }

}
