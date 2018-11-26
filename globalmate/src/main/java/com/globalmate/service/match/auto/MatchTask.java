package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.po.MatchMsg;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.wx.MatchMsgSendService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.StringUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/12 15:54
 * @Description
 */
@Component
public class MatchTask {

    @Autowired
    private MatchService matchService;
    @Autowired
    private NeedService needService;
    @Autowired
    private MatchContext matchContext;
    @Autowired
    private MatchMsgSendService msgSendService;


    @Scheduled(cron = "0 */30 * * * ?")
    public void doMatch() {
        List<Need> needs = needService.listUnHandled(null);
        if (CollectionUtils.isEmpty(needs)) {
            return;
        }

        List<SysMatchNeed> sysMatchNeeds = matchContext.match(needs);

        if (CollectionUtils.isEmpty(sysMatchNeeds)) {
            return ;
        }

        handleSysMatchNeed(sysMatchNeeds);
    }


    public int doMatch(String needId) {
        int result = 0;
        if (StringUtils.isEmpty(needId)) {
            return result;
        }

        Need need = needService.getNeed(needId);
        if (need == null) {
            return result;
        }

        List<SysMatchNeed> sysMatchNeeds = matchContext.matchAll(Lists.newArrayList(need));
        if (CollectionUtils.isEmpty(sysMatchNeeds)) {
            return result;
        }

        handleSysMatchNeed(sysMatchNeeds);

        return sysMatchNeeds.size();
    }

    private void handleSysMatchNeed(List<SysMatchNeed> sysMatchNeeds) {
        matchService.addMatchNeeds(sysMatchNeeds);

        //发送消息
        msgSendService.send(sysMatchNeeds);
    }


}
