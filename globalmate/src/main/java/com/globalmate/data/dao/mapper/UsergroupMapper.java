package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Usergroup;

public interface UsergroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(Usergroup record);

    int insertSelective(Usergroup record);

    Usergroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Usergroup record);

    int updateByPrimaryKey(Usergroup record);
}