package com.globalmate.service.assistance;

import com.globalmate.data.entity.*;
import com.globalmate.data.entity.vo.NeedAggEntity;

import java.util.List;

/**
 * 平台帮助
 */
public interface IAssistService {

    /**
     * 平台匹配用户需求
     * @param need
     * @return 匹配到的可以提供帮助的用户
     */
    List<User> matchDemands(Need need);

    /**
     * 生成一个帮助交易
     * @param matchNeed
     * @return
     */
    SysAssistanceDeal addAssistanceDeal(SysMatchNeed matchNeed);

    /**
     * 获取用户全部有效帮助
     * @param user
     * @return
     */
    List<SysAssistanceDeal> getAssistanceDeal(User user);

    /**
     * 获取推送给我的需求
     * @param user
     * @return
     */
    List<NeedAggEntity> listSOS(User user);

    /**
     * 实施帮助
     * @param needId
     * @param status
     */
    void assist(User user, String needId, String status);

}
