package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Userlink;

public interface UserlinkMapper {
    int deleteByPrimaryKey(String id);

    int insert(Userlink record);

    int insertSelective(Userlink record);

    Userlink selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Userlink record);

    int updateByPrimaryKey(Userlink record);
}