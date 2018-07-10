package com.globalmate.service.match;

import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/10 16:11
 * @Description
 */
public interface IMatchService {

    /**
     * 获取全部匹配的需求
     * @param user
     * @return
     */
    List<SysMatchNeed> listMatch(User user);

    int updateAssistStatus(String matchNeedId, boolean agree);

    List<SysMatchNeed> getByNeedId(String userId, String needId);


}
