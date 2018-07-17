package com.globalmate.service.user;

import com.globalmate.data.dao.mapper.UsergroupMapper;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.Usergroup;
import com.globalmate.data.entity.Userlink;
import com.globalmate.exception.DataNotFoundException;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/7/17 20:25
 * @Description
 */
@Service
public class UserGroupService implements IUserGroupService{

    @Autowired
    private UserLinkService linkService;

    @Autowired
    private UsergroupMapper usergroupMapper;


    @Override
    public Usergroup add(User user, Usergroup usergroup) {
        checkNotNull(usergroup);
        if (usergroup.getId() == null) {
            usergroup.setId(IdGenerator.generateId());
        }
        if (usergroup.getCreator() == null) {
            usergroup.setCreator(user.getId());
        }
        usergroup.setCreateTime(Date.from(Instant.now()));
        int i = usergroupMapper.insert(usergroup);
        if (i > 0) {
            return usergroupMapper.selectByPrimaryKey(usergroup.getId());
        }
        return null;
    }

    @Override
    public Usergroup getUserGroup(String id) {
        if (StringUtils.isNotBlank(id)) {
            return usergroupMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public Usergroup join(User user, String id) {
        checkNotNull(user);
        checkNotNull(id);
        Usergroup userGroup = getUserGroup(id);
        if (userGroup == null) {
            throw new DataNotFoundException("can't find usergroup with id:" + id);
        }
        Userlink userlink = linkService.add(user, id);
        if (userlink != null) {
            return userGroup;
        }
        return null;
    }
}
