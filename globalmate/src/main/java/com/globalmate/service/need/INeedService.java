package com.globalmate.service.need;

import java.util.List;

import com.globalmate.data.entity.Need;
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
     * @param user
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

	String[] getKeyWords(Need need);

	Need getNeed(String needId);
	NeedAggEntity getNeedAgg(String needId);
    
}
