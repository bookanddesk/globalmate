package com.globalmate.service.wx;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.po.MatchMsg;
import com.globalmate.data.entity.po.MsgEntity;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.common.ICreateService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.StringUtils;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author XingJiajun
 * @Date 2018/7/20 16:36
 * @Description
 */
@Service
public class MatchMsgSendService extends WxTempMsgSendService implements IMsgSendService, ICreateService<MatchMsg, SysMatchNeed> {

    @Autowired
    private MsgTemplateService templateService;
    @Autowired
    private NeedService needService;

    public void send(List<SysMatchNeed> sysMatchNeeds) {
        if (CollectionUtils.isEmpty(sysMatchNeeds)) {
            return;
        }
        for (Iterator<SysMatchNeed> iterator = sysMatchNeeds.iterator(); iterator.hasNext();) {
            SysMatchNeed sysMatchNeed = iterator.next();
            if (sysMatchNeed == null || Optional.ofNullable(sysMatchNeed.getMatchMsgCount()).orElse(0) > 0) {
                continue;
            }
            MatchMsg matchMsg = create(sysMatchNeed);
            send(matchMsg);
        }
    }

    @Override
    public MatchMsg create(SysMatchNeed sysMatchNeed) {
        Preconditions.checkNotNull(sysMatchNeed);
        Preconditions.checkNotNull(sysMatchNeed.getProviderId());

        MatchMsg matchMsg = new MatchMsg();
        matchMsg.setToUserId(sysMatchNeed.getProvideId());
        matchMsg.setMsgTempId(getMsgTemplateId(matchMsg));
        matchMsg.setUrl(matchMsg.getUrl());
        matchMsg.setCreateTime(Date.from(Instant.now()));

        String needId = sysMatchNeed.getNeedId();
        NeedAggEntity needAgg = needService.getNeedAgg(needId);
        Optional.ofNullable(needAgg).map(NeedAggEntity::getConceretNeed)
                .ifPresent(x -> {
                    matchMsg.setKeyword1(x.getDestination() == null ? x.getDeparture() :
                            String.format("从 %s 到 %s", x.getDeparture(), x.getDestination()));
                    matchMsg.setKeyword2(x.getTag());
                    matchMsg.setRemark(x.getDescription());
                });

        return matchMsg;
    }


    @Override
    String getMsgTemplateId(MsgEntity msgEntity) {
        return templateService.getTemplateId(msgEntity.getMsgType());
    }
}