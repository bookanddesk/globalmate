package com.globalmate.service.need;

import java.util.List;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.NeedCommon;
import com.globalmate.data.entity.TProvide;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.exception.need.NeedException;

public interface INeedService {
	/**
     * 提交需求
     * @param need
     * @return
     */
	Need commitNeed(Need need) throws NeedException;
    
    /**
     * 更改需求
     * @param need
     * @return
     */
	Need updateNeed(Need need) throws NeedException;
	
	/**
     * 获取用户发布的需求
     * @param user
     * @return
     */
    List<Need> getNeed(User user);


	/**
	 * 根据id批量获取需求
	 * @param ids
	 * @return
	 */
	List<Need> listByIds(List<String> ids);

	/**
	 * 查询未处理的需求
	 * @return
	 */
	List<Need> listUnHandled(User user);

	List<NeedAggEntity> queryLike(Need need);

	List<Need> queryNeedLike(Need need);

	List<Need> associatedQuery(Need need, String searchText);

	/**
	 * 获取需求关键字，数组顺序：【国家，城市，标签，标题】
	 * @param need
	 * @return
	 */
	String[] getKeyWords(Need need);

	Need getNeed(String needId);
	NeedAggEntity getNeedAgg(String needId);

	int closeNeed(String needId);

	Need addCommonNeed(NeedCommon needCommon, User user);
    
}
