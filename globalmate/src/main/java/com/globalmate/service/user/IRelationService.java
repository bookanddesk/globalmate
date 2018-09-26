package com.globalmate.service.user;

import com.globalmate.data.entity.UFansRelations;
import com.globalmate.data.entity.User;
import com.globalmate.exception.user.UserRelationException;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/8/17 10:00
 * @Description
 */
public interface IRelationService {

    UFansRelations addFriend(User targetUser) throws UserRelationException;

    List<UFansRelations> getFriendRelations(String userId);
    
    boolean friendRelationExist(String userId, String targetUserId);


}
