package com.globalmate.service.need;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.globalmate.data.dao.mapper.BuyMapper;
import com.globalmate.data.dao.mapper.CarryMapper;
import com.globalmate.data.entity.Buy;
import com.globalmate.data.entity.Carry;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.service.common.AssistHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
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
    @Autowired
	private BuyMapper buyMapper;
    @Autowired
	private CarryMapper carryMapper;

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
	public List<Need> listUnHandled(User user) {
		Need need = new Need();
		if (user != null) {
			need.setUserId(user.getId());
		}
		return needMapper.selectNeeds(need);
	}

	@Override
	public String[] getKeyWords(Need need) {
		checkNotNull(need);

		String[] keyWords = null;
		String type = need.getType();
		if (StringUtils.isBlank(type)) {
			keyWords = getKeyWords(need.getId(), NeedTypeEnum.valueOf(type));
		}

		if (ArrayUtils.isNotEmpty(keyWords)) {
			return keyWords;
		}

		String description = need.getDescription();
		if (StringUtils.isNotBlank(description)) {
			keyWords = new String[] {null, description};
		}

		return keyWords;
	}

	/**
	 * 获取需求关键字，第一个为地点名，第二个为需求描述
	 * @param needId
	 * @param needTypeEnum
	 * @return
	 */
	public String[] getKeyWords(String needId, NeedTypeEnum needTypeEnum) {
		checkNotNull(needId);
		String[] keyWords = null;
		switch (needTypeEnum) {
			case buy:
				List<Buy> buys = buyMapper.selectByNeedId(needId);
				if (CollectionUtils.isNotEmpty(buys)) {
					Buy buy = buys.get(0);
					keyWords = new String[] {buy.getCountry(), buy.getDescrition(), buy.getGoodsName(), buy.getBrand()};
				}
				break;
			case carry:
				List<Carry> carries = carryMapper.selectByNeedId(needId);
				if (CollectionUtils.isNotEmpty(carries)) {
					Carry carry = carries.get(0);
					keyWords = new String[] {carry.getFrom(), carry.getDescription(), carry.getGoodsName(), carry.getBrand()};
				}
				break;
			case accompany:
				break;
			case clearance:
				break;
			case learn_cooperation:
				break;
			case other:
				break;
			default:
					break;
		}
		return keyWords;
	}

	@Override
	public void handle(Need need, GMEnums.AssistAction action, User user) {
		need.setEnable(String.valueOf(action.getNeedStatus()));
		this.updateNeed(need);
		nextHandler.handle(need, action, user);
	}
}
