package com.globalmate.service.need;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmate.cache.CacheServiceImpl;
import com.globalmate.data.dao.mapper.AccompanyMapper;
import com.globalmate.data.dao.mapper.BuyMapper;
import com.globalmate.data.dao.mapper.CarryMapper;
import com.globalmate.data.dao.mapper.ClearanceMapper;
import com.globalmate.data.dao.mapper.LearnCooperationMapper;
import com.globalmate.data.dao.mapper.NeedMapper;
import com.globalmate.data.entity.Need;
import com.globalmate.exception.need.NeedException;
import com.globalmate.exception.user.UserAddFailException;
import com.globalmate.uitl.IdGenerator;

@Service
public class NeedService implements INeedService{
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
	     checkNotNull(need.getType());
	     //判断need.getType()根据不同类型更新不同需求表
	     int i = needMapper.updateByPrimaryKeySelective(need);
	     if (i == 1) {
	         return needMapper.selectByPrimaryKey(need.getId());
	     }
	     return null;
	}

}
