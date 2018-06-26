package com.globalmate.service.need;

import java.util.List;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.TProvide;
import com.globalmate.data.entity.User;
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
    
}
