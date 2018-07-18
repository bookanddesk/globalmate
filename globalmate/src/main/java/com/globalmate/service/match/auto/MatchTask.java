package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.uitl.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


//    @Scheduled(cron = "0 0/30 * * * ?")
    public void doMatch() {

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


    }



}
