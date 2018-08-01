package com.globalmate.service.need;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.globalmate.data.dao.mapper.*;
import com.globalmate.data.entity.*;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.common.AssistHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmate.cache.CacheServiceImpl;
import com.globalmate.exception.need.NeedException;
import com.globalmate.uitl.IdGenerator;

@Service
public class NeedService extends AssistHandler<Need, GMEnums.AssistAction, User> implements INeedService{
    @Autowired
    private NeedMapper needMapper;
	@Autowired
	private BuyMapper buyMapper;
    @Autowired
	private CarryMapper carryMapper;
    @Autowired
	private AccompanyMapper accompanyMapper;
    @Autowired
	private ClearanceMapper clearanceMapper;
    @Autowired
	private LearnCooperationMapper learnCooperationMapper;
    @Autowired
	private NeedOtherMapper needOtherMapper;

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

	public List<NeedAggEntity> getNeedAgg(User user) {
		List<Need> needs = getNeed(user);
		if (CollectionUtils.isEmpty(needs)) {
			return null;
		}
		List<NeedAggEntity> entities = new ArrayList<>(needs.size());
		for (Need need : needs) {
			NeedAggEntity entity = buildAgg(need);
			entities.add(entity);
		}
		return entities;
	}



	private NeedAggEntity buildAgg(Need need) {
		if (need == null) {
			return null;
		}
		NeedAggEntity entity = new NeedAggEntity();
		entity.setNeed(need);
		entity.setConceretNeed(resolveNeed(need));
		return entity;
	}

	private AbstractNeed resolveNeed(Need need) {
		if (need.getType() == null) {
			return null;
		}
		AbstractNeed abstractNeed = null;
		switch (NeedTypeEnum.valueOf(need.getType())) {
			case buy:
				List<Buy> buys = buyMapper.selectByNeedId(need.getId());
				if (CollectionUtils.isNotEmpty(buys)) {
					abstractNeed = buys.get(0);
				}
				break;
			case carry:
				List<Carry> carries = carryMapper.selectByNeedId(need.getId());
				if (CollectionUtils.isNotEmpty(carries)) {
					abstractNeed = carries.get(0);
				}
				break;
			case accompany:
				List<Accompany> accompanies = accompanyMapper.selectByNeedId(need.getId());
				if (CollectionUtils.isNotEmpty(accompanies)) {
					abstractNeed = accompanies.get(0);
				}
				break;
			case clearance:
				List<Clearance> clearances = clearanceMapper.selectByNeedId(need.getId());
				if (CollectionUtils.isNotEmpty(clearances)) {
					abstractNeed = clearances.get(0);
				}
				break;
			case learn_cooperation:
				List<LearnCooperation> learnCooperations = learnCooperationMapper.selectByNeedId(need.getId());
				if (CollectionUtils.isNotEmpty(learnCooperations)) {
					abstractNeed = learnCooperations.get(0);
				}
				break;
			case other:
				List<NeedOther> needOthers = needOtherMapper.selectByNeedId(need.getId());
				if (CollectionUtils.isNotEmpty(needOthers)) {
					abstractNeed = needOthers.get(0);
				}
				break;
			default:
					break;
		}
		return abstractNeed;
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
		need.setEnable(String.valueOf(GMEnums.NeedStatus.OPEN.getCode()));
		return needMapper.selectNeeds(need);
	}

	@Override
	public List<Need> queryLike(Need need) {
		return needMapper.queryNeeds(need);
	}

	@Override
	public String[] getKeyWords(Need need) {
		checkNotNull(need);

		String[] keyWords = null;
		String type = need.getType();
		if (StringUtils.isNotBlank(type)) {
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

	@Override
	public Need getNeed(String needId) {
		if (StringUtils.isNotBlank(needId)) {
			return needMapper.selectByPrimaryKey(needId);
		}
		return null;
	}

	@Override
	public NeedAggEntity getNeedAgg(String needId) {
		return buildAgg(getNeed(needId));
	}

	/**
	 * 获取需求关键字，第一个为地点名，第二个为需求标签
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
					keyWords = new String[] {buy.getCountry(), NeedTypeEnum.buy.name(),
							buy.getDescrition(), buy.getGoodsName(), buy.getBrand()};
				}
				break;
			case carry:
				List<Carry> carries = carryMapper.selectByNeedId(needId);
				if (CollectionUtils.isNotEmpty(carries)) {
					Carry carry = carries.get(0);
					keyWords = new String[] {carry.getFrom(), NeedTypeEnum.carry.name(),
							carry.getDescription(), carry.getGoodsName(), carry.getBrand()};
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

	public String[] getKeyWords(String needId) {
		AbstractNeed concreteNeed = Optional.ofNullable(getNeedAgg(needId))
				.map(NeedAggEntity::getConceretNeed).orElse(null);
		if (concreteNeed != null) {
			return concreteNeed.getKeywords().toArray(new String[]{});
		}
		return null;
	}

	@Override
	public void handle(Need need, GMEnums.AssistAction action, User user) {
		need.setEnable(String.valueOf(action.getNeedStatus()));
		this.updateNeed(need);
		nextHandler.handle(need, action, user);
	}
}
