package com.globalmate.service.user;

import com.globalmate.data.entity.User;
import com.globalmate.exception.user.UserCheckFailException;

public interface IUserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    User registerUser(User user);


    /**
     * 用户检测
     * @param user
     * @return
     */
    boolean checkUser(User user) throws UserCheckFailException;

    /**
     * 检测用户密码
     * @param phone
     * @param pwd
     * @return
     */
    User checkPwd(String phone, String pwd);

    int falseDelete(User user);

    User updateUser(User user);

}
