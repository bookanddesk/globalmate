package com.globalmate.service.assistance;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.User;

/**
 * 平台帮助
 */
public interface AssistService {

    /**
     * 平台匹配用户需求
     * @param need
     * @return 匹配到的可以提供帮助的用户
     */
    User matchDemands(Need need);



}
