package com.globalmate.controller.assistance;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysAssistanceDeal;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.assistance.AssistService;
import com.globalmate.service.assistance.IAssistService;
import com.globalmate.service.match.auto.MatchTask;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XingJiajun
 * @Date 2018/7/10 15:29
 * @Description
 */

@RestController
@RequestMapping("assist")
public class AssistController extends BaseController {

    @Autowired
    private IAssistService assistService;
    @Autowired
    private UserService userService;
    @Autowired
    private NeedService needService;


    @GetMapping("listSOS")
    public JsonResult listSOS(HttpServletRequest request){
        if (StringUtils.isNotBlank(getParameter(GMConstant.PAGE_NUM))) {
            startPage();
        }
        return buildSuccess(assistService.listSOS(getCurrentUser(request)));
    }


    @GetMapping("listService")
    public JsonResult listService(HttpServletRequest request) {
        List<SysAssistanceDeal> assistanceDeal = assistService.getAssistanceDeal(getCurrentUser(request));
        if (CollectionUtils.isNotEmpty(assistanceDeal)) {
            List<NeedAggEntity> collect = assistanceDeal.stream()
                    .map(x -> needService.getNeedAgg(x.getNeedId()))
                    .collect(Collectors.toList());
            return buildSuccess(collect);
        }
        return buildSuccess();
    }

    @GetMapping("{needId}/{action}")
    public JsonResult assist(@PathVariable("needId") String needId,
                             @PathVariable("action") String action,
                             String providerId) {
        User provider ;
        if (providerId != null) {
            provider = userService.getUser(providerId);
        }else {
            provider = getCurrentUser();
        }
        assistService.assist(provider, needId, action);
        return buildSuccess();
    }

}
