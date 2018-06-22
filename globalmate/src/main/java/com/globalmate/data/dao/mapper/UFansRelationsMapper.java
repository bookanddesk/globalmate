package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.UFansRelations;

public interface UFansRelationsMapper {
    int deleteByPrimaryKey(String id);

    int insert(UFansRelations record);

    int insertSelective(UFansRelations record);

    UFansRelations selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UFansRelations record);

    int updateByPrimaryKey(UFansRelations record);
}