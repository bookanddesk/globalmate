package com.globalmate.service.need;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.service.common.AssistHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmate.cache.CacheServiceImpl;
import com.globalmate.data.dao.mapper.NeedMapper;
import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.User;
import com.globalmate.exception.need.NeedException;
import com.globalmate.uitl.IdGenerator;

@Service
public class NeedService extends AssistHandler<Need, GMEnums.AssistAction, User> implements INeedService{
    @Autowired
    private NeedMapper needMapper;
    @Autowired
    private CacheServiceImpl cacheService;

	@Override
	public Need commitNeed(Need need) throws NeedException {
        if (StringUtils.isBlank(need.getId())) {
        	need.setId(IdGenerator.generateId());
        }
        int insert = needMapper.insert(need);
        Need returnNeed=null;
        if (insert > 0) {
        	returnNeed= needMapper.selectByPrimaryKey(need.getId());
        }
        if(returnNeed!=null)
        	return returnNeed;

        throw new NeedException("新增需求失败！");
	}

	@Override
	public Need updateNeed(Need need) throws NeedException {
		 checkNotNull(need);
	     checkNotNull(need.getId());
	     //判断need.getType()根据不同类型更新不同需求表
	     int i = needMapper.updateByPrimaryKeySelective(need);
	     if (i == 1) {
	         return needMapper.selectByPrimaryKey(need.getId());
	     }
	     return null;
	}

	@Override
	public List<Need> getNeed(User user) {
		Need need = new Need();
        if (user != null && user.getId() != null) {
        	need.setUserId(user.getId());
        }

        List<Need> needs = needMapper.selectNeeds(need);

        return needs;
	}

	@Override
	public List<Need> listByIds(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return null;
		}
		return needMapper.queryByIds(ids);
	}

	@Override
	public void handle(Need need, GMEnums.AssistAction action, User user) {
		need.setEnable(String.valueOf(action.getNeedStatus()));
		this.updateNeed(need);
		nextHandler.handle(need, action, user);
	}
}
