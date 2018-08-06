package com.globalmate.controller.match;

import com.globalmate.controller.BaseController;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.match.auto.MatchTask;
import com.globalmate.uitl.CollectionUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author XingJiajun
 * @Date 2018/7/17 14:54
 * @Description
 */
@RestController
@RequestMapping("match")
public class MatchController extends BaseController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchTask matchTask;


    @GetMapping("{needId}")
    public JsonResult list(@PathVariable("needId") String needId) {
        List<SysMatchNeed> matchNeeds = matchService.getByNeedId(null, needId);
        if (CollectionUtils.isNotEmpty(matchNeeds)) {
            Map<Boolean, List<SysMatchNeed>> collect = matchNeeds.stream().collect(Collectors.groupingBy(SysMatchNeed::getMatchAccept));
            return buildSuccess(collect);
        }
        return buildSuccess();
    }

    @GetMapping("sysMatch")
    public JsonResult sysMatch() throws WxErrorException {
        matchTask.doMatch();
        return buildSuccess();
    }


}
