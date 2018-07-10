package com.globalmate.service.assistance;

import com.globalmate.data.dao.mapper.SysAssistanceDealMapper;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysAssistanceDeal;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.exception.need.NeedException;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.IdGenerator;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/7/10 16:05
 * @Description
 */
@Service
public class AssistService implements IAssistService {

    @Autowired
    private MatchService matchService;
    @Autowired
    private NeedService needService;
    @Autowired
    private UserService userService;

    @Autowired
    private SysAssistanceDealMapper assistanceDealMapper;


    @Override
    public List<User> matchDemands(Need need) {
        return null;
    }

    @Override
    public SysAssistanceDeal addAssistanceDeal(SysMatchNeed matchNeed) {
        return null;
    }

    @Override
    public List<SysAssistanceDeal> getAssistanceDeal(User user) {
        SysAssistanceDeal assistanceDeal = new SysAssistanceDeal();
        if (user != null) {
            assistanceDeal.setuProviderId(user.getId());
        }
        assistanceDeal.setAssistStatus(GMEnums.ServiceStatus.END.getValue());
        return assistanceDealMapper.queryRecords(assistanceDeal);
    }

    @Override
    public List<Need> listSOS(User user) {
        List<SysMatchNeed> sysMatchNeeds = matchService.listMatch(user);
        if (CollectionUtils.isEmpty(sysMatchNeeds)) {
            return null;
        }
        List<String> needIds = sysMatchNeeds.stream().map(SysMatchNeed::getNeedId).collect(Collectors.toList());
        return needService.listByIds(needIds);
    }

    @Override
    @Transactional
    public void assist(User user, String needId, String status) {
        checkNotNull(user);
        checkNotNull(needId);
        checkNotNull(status);

        GMEnums.AssistAction assistAction = GMEnums.AssistAction.valueOf(status);

        //更新匹配采纳状态
        List<SysMatchNeed> matchNeeds = matchService.getByNeedId(user.getId(), needId);
        if (CollectionUtils.isEmpty(matchNeeds)) {
            throw new IllegalStateException("matchNeeds not found with providerId:[ "
                    + user.getId() + "], needId:[" + needId + "]");
        }

        if (matchNeeds.size() > 0) {
            //去除多余匹配
        }

        SysMatchNeed matchNeed = matchNeeds.get(0);

        matchService.updateAssistStatus(matchNeed.getId(), assistAction.equals(GMEnums.AssistAction.AGREE));

        if (GMEnums.AssistAction.REFUSE.equals(assistAction)) {
            return;
        }

        //更新需求状态
        List<Need> needs = needService.listByIds(Lists.newArrayList(needId));
        if (CollectionUtils.isEmpty(needs)) {
            throw new NeedException("need not found with id:" + needId);
        }
        Need need = needs.get(0);
        need.setEnable(String.valueOf(assistAction.getNeedStatus()));
        needService.updateNeed(need);

        //更新帮助交易状态
        SysAssistanceDeal assistanceDeal = new SysAssistanceDeal();
        assistanceDeal.setuProviderId(user.getId());
        assistanceDeal.setNeedId(needId);
        List<SysAssistanceDeal> assistanceDeals = assistanceDealMapper.queryRecords(assistanceDeal);
        if (CollectionUtils.isEmpty(assistanceDeals)) {
            if (!GMEnums.AssistAction.AGREE.equals(assistAction)) {
                throw new IllegalStateException("assistanceDeals is null, but assistAction is " + assistAction.getValue());
            }
            //生成新帮助交易
            assistanceDeal.setId(IdGenerator.generateId());
            assistanceDeal.setNeedId(needId);
            assistanceDeal.setuNeederId(need.getUserId());
            assistanceDeal.setuNeederName(userService.getName(need.getUserId()));
            assistanceDeal.setuProviderName(user.getName());
            assistanceDeal.setAssistStatus(assistAction.getValue());
            assistanceDeal.setAssistCreateTime(Date.from(Instant.now()));
            assistanceDeal.setAssistModifyTime(Date.from(Instant.now()));
            assistanceDeal.setProvideId(matchNeed.getProvideId());
            assistanceDealMapper.insertSelective(assistanceDeal);
        } else {
            assistanceDeal.setAssistStatus(assistAction.getValue());
            assistanceDealMapper.updateByPrimaryKeySelective(assistanceDeal);
        }

    }
}
