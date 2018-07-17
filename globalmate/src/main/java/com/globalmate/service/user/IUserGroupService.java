package com.globalmate.service.user;

import com.globalmate.data.entity.User;
import com.globalmate.data.entity.Usergroup;

/**
 * @author XingJiajun
 * @Date 2018/7/17 20:22
 * @Description
 */
public interface IUserGroupService {

    Usergroup add(User user, Usergroup usergroup);

    Usergroup getUserGroup(String id);

    Usergroup join(User user, String id);

}
