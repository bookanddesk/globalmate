package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.User;
import com.globalmate.uitl.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectUserCount(User user);

    User selectByPhone(@Param("phone") String phone);

    int falseDelete(@Param("userId") String userId);

    List<String> listSchool();

    List<User> queryUsers(User user);

    List<User> selectByOpenId(String openId);

    int updateSubscribeStatus(@Param("openId") String openId, @Param("subscribe") Boolean subscribe);

    int updateExt1(@Param("id")String id, @Param("ext1")String ext1);

    List<User> queryByLoginTime(@Param("user") User user, @Param("uExt1") String uExt1);

}