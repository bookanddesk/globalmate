package com.globalmate.service.user;

import com.globalmate.data.entity.UTag;
import com.globalmate.data.entity.User;

import javax.jws.soap.SOAPBinding;

/**
 * 用户标签
 */
public interface ITagService {
    /**
     * 添加一个标签
     * @param tag
     * @return
     */
    UTag addTag(UTag tag);

    /**
     * 删除一个标签
     * @param tag
     * @return
     */
    int deleteTag(UTag tag);

    /**
     * 给用户设置一个标签
     * @param user
     * @param tag
     * @return
     */
    User addTag2User(User user, UTag tag);

    /**
     * 删除用户的一个标签
     * @param user
     * @param tag
     * @return
     */
    User deleteUserTag(User user, UTag tag);


}
