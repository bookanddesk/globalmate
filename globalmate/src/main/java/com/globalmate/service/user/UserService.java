package com.globalmate.service.user;

import com.globalmate.cache.CacheServiceImpl;
import com.globalmate.cache.LocalCache;
import com.globalmate.data.dao.mapper.UserMapper;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.exception.user.UseNotFoundException;
import com.globalmate.exception.user.UserAddFailException;
import com.globalmate.exception.user.UserCheckFailException;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.RegexUtils;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
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

        //编辑了个人资料就是银牌用户
        String userTag = user.getUserTag();
        boolean needUpTg = StringUtils.isEmpty(userTag);
        if (!needUpTg) {
            try {
                needUpTg = GMEnums.vTag.vSilver.compareTo(GMEnums.vTag.valueOf(userTag)) < 0;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if (needUpTg) {
            user.setUserTag(GMEnums.vTag.vSilver.name());
        }

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
        User user = new User();
        user.setEnable(GMConstant.ONE_STR_VALUE);
        return listUsersLike(user);
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
        if (StringUtils.isEmpty(name)) {
            User user = getUser(id);
            if (user != null) {
                name = user.getName();
                if (StringUtils.isNotEmpty(name)) {
                    userCache.put(id, name);
                }else {
                    name = user.getNikename();
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
    public List<User> selectWXUser(String openId) {
        if (StringUtils.isNotBlank(openId)) {
            return userMapper.selectByOpenId(openId);
        }
        return null;
    }

    @Override
    public int handleWxUser(WxMpUser wxMpUser) {
        List<User> users = selectWXUser(wxMpUser.getOpenId());
        if (CollectionUtils.isEmpty(users)) {
            User user = new User();
            user.setId(IdGenerator.generateId());
            user.setEnable(GMConstant.ONE_STR_VALUE);
            copyProperties(user, wxMpUser);
            user.setCreateTime(Date.from(Instant.now()));
            user.setuExt2(generateMemberId());
            //关注就是铜牌会员 2018-11-05
            user.setUserTag(GMEnums.vTag.vCopper.name());
            return userMapper.insert(user);
        }

        User user = users.get(0);
        copyProperties(user, wxMpUser);
        user.setEnable(GMConstant.ONE_STR_VALUE);
        user.setModifyTime(Date.from(Instant.now()));
        if (StringUtils.isEmpty(user.getuExt2())) {
            user.setuExt2(generateMemberId());
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public String getOpenId(String id) {
        if (id == null) {
            return null;
        }
        String chKey = GMConstant.CACHE_OPENID + id,
                openId = cacheService.getSerializer(chKey, String.class);
        if (openId == null) {
            User user = getUser(id);
            if (user != null) {
                openId = user.getOpenid();
                if (openId != null) {
                    cacheService.setSerializer(chKey, openId, GMConstant.TOKEN_EXP_TIME);
                }
            }
        }
        return openId;
    }

    @Override
    public int userUnsubscribe(String openid) {
        if (StringUtils.isBlank(openid)) {
            return -1;
        }
        return userMapper.updateSubscribeStatus(openid, false);
    }

    @Override
    public List<User> queryByLoginTime(User user, String utilDateStr) {
        return userMapper.queryByLoginTime(user, utilDateStr);
    }

    @Override
    public String generateMemberId() {
        String lastMemberId = Optional.ofNullable(userMapper.selectMaxExt2()).orElse(GMConstant.ZERO_STR_VALUE);
        return IdGenerator.generateMemberId(lastMemberId);
    }

    @Override
    public User updateUserTag(String userId, String tag) {
        User user = new User();
        user.setId(userId);
        user.setUserTag(tag);
        return updateUser(user);
    }

    @Override
    public String getUserTag(String userId) {
        checkNotNull(userId);
        return userMapper.queryUserTag(userId);
    }

    private User copyProperties(User user, WxMpUser wxMpUser) {
        user.setNikename(com.globalmate.uitl.StringUtils.replaceEmoji(wxMpUser.getNickname()));
        user.setSubscribe(wxMpUser.getSubscribe());
        user.setOpenid(wxMpUser.getOpenId());
        user.setSex(wxMpUser.getSexDesc());
        user.setLanguage(wxMpUser.getLanguage());
//        user.setCity(wxMpUser.getCity() == null ? wxMpUser.getProvince() : wxMpUser.getCity());
        user.setCountry(wxMpUser.getCountry());
        user.setPic(wxMpUser.getHeadImgUrl());
//        user.setCreateTime(new Date(wxMpUser.getSubscribeTime()));
        user.setSubscribe_scene(wxMpUser.getSubscribeScene());
        user.setProvince(wxMpUser.getProvince());
        return user;
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
            updateLoginTime(user);
        }
        cacheService.setSerializer(token, user, GMConstant.TOKEN_EXP_TIME);
        return token;
    }

    @Override
    public User getTokenUser(String token) {
        return cacheService.getSerializer(token, User.class);
    }


    public int updateLoginTime(User user) {
        return userMapper.updateExt1(user.getId(), DateUtil.format(Date.from(Instant.now()), DateUtil.FMT_DATETIME));
    }


}
