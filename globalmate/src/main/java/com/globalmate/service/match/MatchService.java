package com.globalmate.service.match;

import com.globalmate.data.dao.mapper.SysMatchNeedMapper;
import com.globalmate.data.entity.*;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.service.common.AssistHandler;
import com.globalmate.service.common.ICreateService;
import com.globalmate.service.match.auto.IMatchExecuteSevice;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XingJiajun
 * @Date 2018/7/10 16:12
 * @Description
 */
@Service
public class MatchService extends AssistHandler<Need, GMEnums.AssistAction, User> implements IMatchService,
        ICreateService<SysMatchNeed, TProvide>, IMatchExecuteSevice {

    @Autowired
    private SysMatchNeedMapper matchNeedMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<SysMatchNeed> listMatch(User user) {
        SysMatchNeed matchNeed = new SysMatchNeed();
        if (user != null){
            matchNeed.setProviderId(user.getId());
        }
        return matchNeedMapper.queryMatchNeeds(matchNeed);
    }

    @Override
    public int updateAssistStatus(List<String> matchNeedIds, boolean agree) {
        if (CollectionUtils.isEmpty(matchNeedIds)) {
            return -1;
        }
        return matchNeedMapper.updateMatchAccept(matchNeedIds, agree);
    }

    @Override
    public List<SysMatchNeed> getByNeedId(String userId, String needId) {
        SysMatchNeed sysMatchNeed = new SysMatchNeed();
        if (userId != null) {
            sysMatchNeed.setProviderId(userId);
        }
        if (needId != null) {
            sysMatchNeed.setNeedId(needId);
        }
        return matchNeedMapper.queryMatchNeeds(sysMatchNeed);
    }

    @Override
    public int addMatchNeed(SysMatchNeed sysMatchNeed) {
        if (sysMatchNeed != null) {
            return matchNeedMapper.insert(sysMatchNeed);
        }
        return -1;
    }

    @Override
    public int addMatchNeeds(List<SysMatchNeed> matchNeeds) {
        if (CollectionUtils.isNotEmpty(matchNeeds)) {
            return matchNeedMapper.insertBatch(matchNeeds);
        }
        return -1;
    }


    @Override
    public void handle(Need need, GMEnums.AssistAction action, User user) {

        List<SysMatchNeed> matchNeeds = this.getByNeedId(user.getId(), need.getId());
        if (CollectionUtils.isEmpty(matchNeeds)) {
            //TODO 如果是通过搜索需求后主动提供的帮助且没有相应的系统匹配信息就自动生成一条匹配记录
//            throw new IllegalStateException("matchNeeds not found with providerId:[ "
//                    + user.getId() + "], needId:[" + need.getId() + "]");

            SysMatchNeed matchNeed = create(user, need);
            matchNeed.setMatchAccept(true);
            addMatchNeed(matchNeed);
        } else {
            if (matchNeeds.size() > 0) {
                //去除多余匹配
            }

            SysMatchNeed matchNeed = matchNeeds.get(0);
            boolean agree = action.equals(GMEnums.AssistAction.AGREE);
            this.updateAssistStatus(Lists.newArrayList(matchNeed.getId()), agree);

            if (agree) {
                this.setLocal(StringUtils.join_(need.getId(), user.getId()), matchNeed.getProvideId());
                //更新其他匹配的状态
                List<SysMatchNeed> sysMatchNeeds = this.getByNeedId(null, need.getId());
                if (CollectionUtils.isNotEmpty(sysMatchNeeds)) {
                    this.updateAssistStatus(sysMatchNeeds.stream()
                            .map(SysMatchNeed::getId)
                            .filter(x -> StringUtils.equals(x, need.getId()))
                            .collect(Collectors.toList()),
                            !agree);
                }
            }

            if (GMEnums.AssistAction.REFUSE.equals(action)) {
                return;
            }
        }

        nextHandler.handle(need, action, user);

    }

    @Override
    public void matchAction() {

    }

    @Override
    public SysMatchNeed create(TProvide provide) {
        SysMatchNeed matchNeed = new SysMatchNeed();
        matchNeed.setId(IdGenerator.generateId());
        if (provide != null) {
            matchNeed.setProvideId(provide.getId());
            matchNeed.setProviderId(provide.getuId());
            matchNeed.setProviderName(provide.getuName());
        }
        matchNeed.setMatchCreateTime(Date.from(Instant.now()));
        return matchNeed;
    }

    public SysMatchNeed create(User user) {
        SysMatchNeed matchNeed = new SysMatchNeed();
        matchNeed.setId(IdGenerator.generateId());
        if (user != null) {
            matchNeed.setProviderId(user.getId());
            matchNeed.setProviderName(user.getNikename());
        }
        matchNeed.setMatchCreateTime(Date.from(Instant.now()));
        return matchNeed;
    }

    public SysMatchNeed create(User user, Need need) {
        SysMatchNeed matchNeed = create(user);
        matchNeed.setNeedId(need.getId());
        matchNeed.setuNeedId(need.getUserId());
        matchNeed.setuNeedName(userService.getName(need.getUserId()));
        return matchNeed;
    }
}
