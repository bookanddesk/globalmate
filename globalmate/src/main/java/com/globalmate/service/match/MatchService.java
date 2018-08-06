package com.globalmate.service.match;

import com.globalmate.data.dao.mapper.SysMatchNeedMapper;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.builder.SysMatchNeedBuilder;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.service.common.AssistHandler;
import com.globalmate.service.common.ICreateService;
import com.globalmate.service.match.auto.IMatchExecuteSevice;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author XingJiajun
 * @Date 2018/7/10 16:12
 * @Description
 */
@Service
public class MatchService extends AssistHandler<Need, GMEnums.AssistAction, User> implements IMatchService,
        ICreateService<SysMatchNeed, Need>, IMatchExecuteSevice {

    @Autowired
    private SysMatchNeedMapper matchNeedMapper;

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
    public List<SysMatchNeed> queryLike(SysMatchNeed matchNeed) {
        if (matchNeed != null) {
            return matchNeedMapper.queryMatchNeeds(matchNeed);
        }
        return null;
    }

    @Override
    public int addMsgSendCount(SysMatchNeed matchNeed) {
        if (matchNeed != null && matchNeed.getId() != null) {
            matchNeed.setMatchMsgCount(Optional.ofNullable(matchNeed.getMatchMsgCount()).orElse(0) + 1);
            return matchNeedMapper.updateByPrimaryKeySelective(matchNeed);
        }
        return 0;
    }


    @Override
    public void handle(Need need, GMEnums.AssistAction action, User user) {

        List<SysMatchNeed> matchNeeds = this.getByNeedId(user.getId(), need.getId());
        if (CollectionUtils.isEmpty(matchNeeds)) {
            //TODO 如果是通过搜索需求后主动提供的帮助且没有相应的系统匹配信息就自动生成一条匹配记录
//            throw new IllegalStateException("matchNeeds not found with providerId:[ "
//                    + user.getId() + "], needId:[" + need.getId() + "]");

            SysMatchNeed matchNeed = new SysMatchNeedBuilder().build().need(need).user(user).get();
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
    public SysMatchNeed create(Need need) {
        return new SysMatchNeedBuilder().build().need(need).get();
    }
}
