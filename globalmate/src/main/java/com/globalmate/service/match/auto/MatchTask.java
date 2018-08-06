package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.po.MatchMsg;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.wx.MatchMsgSendService;
import com.globalmate.uitl.CollectionUtils;
import com.google.common.base.Preconditions;
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


    @Scheduled(cron = "0 */3 * * * ?")
    public void doMatch() {
        System.out.println("match match --run-----------------------------count:" +
                ++anInt + "---threadId:" + Thread.currentThread().getId());
        List<Need> needs = needService.listUnHandled(null);
        if (CollectionUtils.isEmpty(needs)) {
            return;
        }

        List<SysMatchNeed> sysMatchNeeds = matchContext.match(needs);

        if (CollectionUtils.isEmpty(sysMatchNeeds)) {
            return ;
        }

        matchService.addMatchNeeds(sysMatchNeeds);

        //发送消息
        msgSendService.send(sysMatchNeeds);


    }

    int anInt = 0;
//    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {
        System.out.println("match match --run-----------------------------count:" +
                ++anInt + "---threadId:" + Thread.currentThread().getId());
    }

}
