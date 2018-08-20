package com.globalmate.service.assistance;

import com.globalmate.data.dao.mapper.SysAssistanceDealMapper;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysAssistanceDeal;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.exception.need.NeedException;
import com.globalmate.service.common.AssistHandler;
import com.globalmate.service.common.ICreateService;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/7/10 16:05
 * @Description
 */
@Service
public class AssistService extends AssistHandler<Need, GMEnums.AssistAction, User>
        implements IAssistService, ICreateService<SysAssistanceDeal, User>{

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
//        SysAssistanceDeal assistanceDeal = new SysAssistanceDeal();
//        if (user != null) {
//            assistanceDeal.setuProviderId(user.getId());
//        }
//        assistanceDeal.setAssistStatus(GMEnums.ServiceStatus.END.getValue());
        return assistanceDealMapper.queryAssists(user.getId());
    }

    @Override
    public List<NeedAggEntity> listSOS(User user) {
        List<SysMatchNeed> sysMatchNeeds = matchService.listMatch(user);
        if (CollectionUtils.isEmpty(sysMatchNeeds)) {
            return null;
        }
        List<NeedAggEntity> entities = sysMatchNeeds.stream()
                .map(x -> needService.getNeedAgg(x.getNeedId()))
                .collect(Collectors.toList());
        return entities;
    }

    @Override
    @Transactional
    public void assist(User user, String needId, String status) {
        checkNotNull(user);
        checkNotNull(needId);
        checkNotNull(status);

        GMEnums.AssistAction assistAction = GMEnums.AssistAction.valueOf(status.toUpperCase());
        List<Need> needs = needService.listByIds(Lists.newArrayList(needId));
        if (CollectionUtils.isEmpty(needs)) {
            throw new NeedException("need not found with id:" + needId);
        }
        Need need = needs.get(0);

        if (StringUtils.equals(need.getUserId(), user.getId())) {
            if (assistAction.equals(GMEnums.AssistAction.COMPLETE)) {
                need.setEnable(String.valueOf(assistAction.getNeedStatus()));
                needService.updateNeed(need);
                return;
            }
            throw new IllegalStateException("user can only do complete action oneself:[ "
                    + user.getId() + "], needId:[" + need.getId() + "]");
        }

        matchService.handle(need, assistAction, user);
    }

    @Override
    public SysAssistanceDeal create(User user) {
        SysAssistanceDeal assistanceDeal = new SysAssistanceDeal();
        assistanceDeal.setId(IdGenerator.generateId());
        if (user != null) {
            assistanceDeal.setuProviderId(user.getId());
            assistanceDeal.setuProviderName(user.getName());
        }
        assistanceDeal.setAssistCreateTime(Date.from(Instant.now()));
        assistanceDeal.setAssistModifyTime(Date.from(Instant.now()));
        return assistanceDeal;
    }


    @Override
    public void handle(Need need, GMEnums.AssistAction assistAction, User user) {
        String needId = need.getId();
        SysAssistanceDeal assistanceDeal = new SysAssistanceDeal();
        assistanceDeal.setuProviderId(user.getId());
        assistanceDeal.setNeedId(needId);
        List<SysAssistanceDeal> assistanceDeals = assistanceDealMapper.queryRecords(assistanceDeal);
        if (CollectionUtils.isEmpty(assistanceDeals)) {
            if (!GMEnums.AssistAction.AGREE.equals(assistAction)) {
                throw new IllegalStateException("assistanceDeals is null, but assistAction is " + assistAction.getValue());
            }
            //生成新帮助交易
            SysAssistanceDeal sysAssistanceDeal = create(user);
            sysAssistanceDeal.setNeedId(needId);
            sysAssistanceDeal.setuNeederId(need.getUserId());
            sysAssistanceDeal.setuNeederName(userService.getName(need.getUserId()));
            sysAssistanceDeal.setAssistStatus(assistAction.getValue());
            Optional.ofNullable(matchService.removeLocal(StringUtils.join_(needId, user.getId())))
                    .ifPresent(x ->sysAssistanceDeal.setProvideId(String.valueOf(x)));
            assistanceDealMapper.insertSelective(sysAssistanceDeal);
        } else {
            assistanceDeal.setAssistStatus(assistAction.getValue());
            assistanceDealMapper.updateByPrimaryKeySelective(assistanceDeal);
        }
    }

    @PostConstruct
    public void assistHanlerChain(){
        matchService.setNextHandler(needService);
        needService.setNextHandler(this);
    }

}
