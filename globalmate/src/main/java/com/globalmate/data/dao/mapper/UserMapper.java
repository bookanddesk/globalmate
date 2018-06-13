package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
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
}