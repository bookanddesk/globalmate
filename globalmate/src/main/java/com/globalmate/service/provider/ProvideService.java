package com.globalmate.service.provider;

import com.globalmate.data.entity.Service;
import com.globalmate.data.entity.TProvide;
import com.globalmate.data.entity.User;

import java.util.List;

/**
 * 服务提供
 */
public interface ProvideService {

    /**
     * 用户添加一种可提供的服务
     * @param user
     * @param provide
     * @return
     */
    TProvide addProvide(User user, TProvide provide);

    TProvide updateProvide(User user, TProvide provide);

    /**
     * 获取用户可提供的所有服务
     * @param user
     * @return
     */
    List<TProvide> getProvide(User user);



}
