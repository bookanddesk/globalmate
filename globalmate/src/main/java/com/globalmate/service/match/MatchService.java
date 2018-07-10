package com.globalmate.service.match;

import com.globalmate.data.dao.mapper.SysMatchNeedMapper;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/10 16:12
 * @Description
 */
@Service
public class MatchService implements IMatchService {

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
    public int updateAssistStatus(String matchNeedId, boolean agree) {
        Preconditions.checkNotNull(matchNeedId);
        SysMatchNeed matchNeed = new SysMatchNeed();
        matchNeed.setId(matchNeedId);
        matchNeed.setMatchAccept(agree);
        return matchNeedMapper.updateByPrimaryKeySelective(matchNeed);
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


}
