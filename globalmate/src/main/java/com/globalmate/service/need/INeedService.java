package com.globalmate.service.need;

import com.globalmate.data.entity.Need;
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
    
}
