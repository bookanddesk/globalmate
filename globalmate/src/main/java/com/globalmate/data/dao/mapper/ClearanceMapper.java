package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Clearance;

public interface ClearanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Clearance record);

    int insertSelective(Clearance record);

    Clearance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clearance record);

    int updateByPrimaryKey(Clearance record);
}