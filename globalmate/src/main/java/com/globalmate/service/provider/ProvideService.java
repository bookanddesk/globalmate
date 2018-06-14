package com.globalmate.service.provider;

import com.globalmate.data.entity.Service;
import com.globalmate.data.entity.User;

import java.util.List;

/**
 * 服务提供
 */
public interface ProvideService {

    /**
     * 用户提供了一项服务
     * @param user
     * @param service
     * @return
     */
    Service addService(User user, Service service);

    /**
     * 获取用户所提供的全部服务
     * @param user
     * @return
     */
    List<Service> getService(User user);

    /**
     * 用户添加一种可提供的服务
     * @param t
     * @param <T>
     * @return
     */
    <T> T addSupport (User user, T t);

}
