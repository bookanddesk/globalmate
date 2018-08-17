package com.globalmate.service.user;

import com.globalmate.data.dao.mapper.UFansRelationsMapper;
import com.globalmate.data.entity.UFansRelations;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.exception.user.UserRelationException;
import com.globalmate.service.common.ICreateService;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/8/17 10:02
 * @Description
 */
@Service
public class RelationService implements IRelationService, ICreateService<UFansRelations, User> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UFansRelationsMapper relationsMapper;

    @Override
    public UFansRelations addFriend(User targetUser) throws UserRelationException {
        checkNotNull(targetUser);
        checkNotNull(targetUser.getId());

        User user = (User) request.getAttribute(GMConstant.USER);
        checkNotNull(user);
        if (StringUtils.equals(user.getId(), targetUser.getId())) {
            throw new UserRelationException("userId : [" + user.getId() + "] equals with targetUserId : ["
                    + targetUser.getId() + "] !");
        }

        short friendCode = GMEnums.UserRelationType.friend.getCode();
        int relationsCount = relationsMapper.relationsCount(user.getId(), targetUser.getId(), friendCode);
        if (relationsCount > 0) {
            throw new UserRelationException("friend Relation already exists with userId : ["
                    + user.getId() + "] and targetUserId : [" + targetUser.getId() + "] !");
        }

        UFansRelations relations = create(targetUser);
        relations.setRelationType(friendCode);
        int insert = relationsMapper.insert(relations);
        if (insert > 0) {
            return relationsMapper.selectByPrimaryKey(relations.getId());
        }
        return null;
    }

    @Override
    public List<UFansRelations> getFriendRelations(String userId) {
        return null;
    }

    @Override
    public UFansRelations create(User targetUser) {
        User user = (User) request.getAttribute(GMConstant.USER);
        if (user == null) {
            return null;
        }
        UFansRelations relations = new UFansRelations();
        relations.setId(IdGenerator.generateId());
        relations.setuId(user.getId());
        relations.setuName(user.getNikename());
        relations.setCreateTime(Date.from(Instant.now()));
        if (targetUser != null) {
            relations.setuRelatedId(targetUser.getId());
            relations.setuRelatedName(targetUser.getNikename());
        }
        return relations;
    }
}
