package com.globalmate.service.user;

import com.globalmate.data.entity.UCredit;
import com.globalmate.data.entity.User;

/**
 * 用户信用
 */
public interface ICreditService {

    /**
     * 增加一个信用等级
     * @param credit
     * @return
     */
    UCredit addCredit(UCredit credit);

    /**
     * 删除一个信用等级
     * @param credit
     * @return
     */
    int deleteCredit(UCredit credit);

    /**
     * 设置用户信用等级，应该是唯一的
     * @param user
     * @param credit
     * @return
     */
    User setUserCredit(User user, UCredit credit);

    /**
     * 调整用户信用等级
     * @param user
     * @param credit 可为空
     * @return
     */
    User updateUserCredit(User user, UCredit credit);

}
