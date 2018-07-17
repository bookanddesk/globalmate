package com.globalmate.service.user;

import com.globalmate.data.entity.User;
import com.globalmate.data.entity.Userlink;

/**
 * @author XingJiajun
 * @Date 2018/7/17 20:43
 * @Description
 */
public interface IUserLinkService {

    Userlink add(User user, String targetId);

}
