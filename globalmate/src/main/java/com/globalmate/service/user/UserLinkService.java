package com.globalmate.service.user;

import com.globalmate.data.dao.mapper.UserlinkMapper;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.Userlink;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.service.common.ICreateService;
import com.globalmate.uitl.IdGenerator;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/7/17 20:45
 * @Description
 */
@Service
public class UserLinkService implements IUserLinkService, ICreateService<Userlink, User> {

    @Autowired
    private UserlinkMapper userlinkMapper;

    @Override
    public Userlink add(User user, String targetId) {
        checkNotNull(user);
        checkNotNull(user.getId());
        checkNotNull(targetId);
        Userlink userlink = create(user);
        userlink.setTargetId(targetId);
        userlink.setType(GMEnums.UserLinkType.ORGANIZATION.getValue());
        int insert = userlinkMapper.insert(userlink);
        if (insert > 0) {
            return userlinkMapper.selectByPrimaryKey(userlink.getId());
        }
        return null;
    }

    @Override
    public Userlink create(User user) {
        Userlink userlink = new Userlink();
        userlink.setId(IdGenerator.generateId());
        if (user != null) {
            userlink.setUserId(user.getId());
        }
        return userlink;
    }
}
