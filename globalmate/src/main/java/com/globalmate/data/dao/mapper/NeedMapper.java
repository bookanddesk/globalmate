package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Need;

public interface NeedMapper {
    int deleteByPrimaryKey(String id);

    int insert(Need record);

    int insertSelective(Need record);

    Need selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Need record);

    int updateByPrimaryKey(Need record);
}