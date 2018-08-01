package com.globalmate.service.user;

import com.globalmate.cache.CacheServiceImpl;
import com.globalmate.cache.LocalCache;
import com.globalmate.data.dao.mapper.UserMapper;
import com.globalmate.data.entity.User;
import com.globalmate.exception.user.UseNotFoundException;
import com.globalmate.exception.user.UserAddFailException;
import com.globalmate.exception.user.UserCheckFailException;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.RegexUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService implements IUserService, ITokenservice {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheServiceImpl cacheService;

    private static final LocalCache<String, String> userCache = new LocalCache<>();

    @Override
    public User registerUser(User user) {
        if (StringUtils.isBlank(user.getId())) {
            user.setId(IdGenerator.generateId());
        }
        user.setCreateTime(Date.from(Instant.now()));
        user.setEnable(GMConstant.ONE_STR_VALUE);
        user.setNice(GMConstant.ZERO_INT_VALUE);
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return userMapper.selectByPrimaryKey(user.getId());
        }
        throw new UserAddFailException("新增用户失败！");
    }

    @Override
    public boolean checkUser(User user) throws UserCheckFailException {

        //昵称重复检测
        String nikename = user.getNikename();
        if (StringUtils.isBlank(nikename)) {
            throw new UserCheckFailException("昵称不能为空！");
        }
        User cUser = new User();
        cUser.setNikename(nikename);
        int count = userMapper.selectUserCount(cUser);
        if (count > 0) {
            throw new UserCheckFailException("这个昵称已被占用！");
        }

        //手机号检测
        String phone = user.getPhone();
        if (StringUtils.isBlank(phone)) {
            throw new UserCheckFailException("手机号码不能为空！");
        }
        if (!RegexUtils.checkMobile(phone)) {
            throw new UserCheckFailException("手机号码有误，请重新输入！");
        }
        cUser.setNikename(null);
        cUser.setPhone(phone);
        count = userMapper.selectUserCount(cUser);
        if (count > 0) {
            throw new UserCheckFailException("这个手机号码已注册过，请直接登录！");
        }


        //邮箱检测
        String email = user.getEmail();
        if (StringUtils.isNotBlank(email) && !RegexUtils.checkEmail(email)) {
            throw new UserCheckFailException("邮箱格式错误，请重新输入！");
        }

        //密码检测
        String password = user.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new UserCheckFailException("密码不能为空！");
        }
        if (!RegexUtils.checkPwd(password)) {
            throw new UserCheckFailException("密码必须大于6位！");
        }

        //身份证检测
        String idNumber = user.getIdNumber();
        if (StringUtils.isNotBlank(idNumber) && !RegexUtils.checkIdCard(idNumber)) {
            throw new UserCheckFailException("身份证号不合法，请重新输入！");
        }

        return true;
    }

    @Override
    public User checkPwd(String phone, String pwd) {
        checkNotNull(phone);
        User user = userMapper.selectByPhone(phone);
        if (user != null && StringUtils.equals(pwd, user.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    public int falseDelete(User user) {
        checkNotNull(user);
        checkNotNull(user.getId());
        return userMapper.falseDelete(user.getId());
    }

    @Override
    public User updateUser(User user) {
        checkNotNull(user);
        checkNotNull(user.getId());
//        checkNotNull(user.getPhone());
//        checkNotNull(user.getNikename());
        if (userMapper.selectByPrimaryKey(user.getId()) == null) {
            throw new UseNotFoundException("未找到该用户！[" + user.getId() + "]");
        }
        user.setModifyTime(Date.from(Instant.now()));
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i == 1) {
            return userMapper.selectByPrimaryKey(user.getId());
        }
        return null;
    }

    @Override
    public List<String> listSchool() {
        return userMapper.listSchool();
    }

    @Override
    public List<User> listAllUsers() {
        return listUsersLike(null);
    }

    @Override
    public List<User> listUsersLike(User user) {
        return userMapper.queryUsers(Optional.ofNullable(user).orElse(new User()));
    }

    @Override
    public User getUser(String id) {
        if (id != null) {
            return userMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public String getName(String id) {
        if (id == null) {
            return null;
        }
        String name = userCache.get(id);
        if (name == null) {
            User user = getUser(id);
            if (user != null) {
                name = user.getName();
                if (name != null) {
                    userCache.put(id, name);
                }
            }
        }
        return name;
    }

    @Override
    public User updateNice(String userId, Integer niceValue) {
        if (niceValue == null || niceValue == 0) {
            return null;
        }
        User user = getUser(userId);
        if (user == null) {
            return null;
        }
        user.setNice(Optional.ofNullable(user.getNice()).orElse(0) + niceValue);
        return updateUser(user);
    }

    @Override
    public String generateToken() {
        return IdGenerator.generateId();
    }

    @Override
    public String putUserToken(String token, User user) {
        checkNotNull(user);
        if (StringUtils.isBlank(token)) {
            token = generateToken();
        }
        cacheService.setSerializer(token, user, GMConstant.TOKEN_EXP_TIME);
        return token;
    }

    @Override
    public User getTokenUser(String token) {
        return cacheService.getSerializer(token, User.class);
    }


}
