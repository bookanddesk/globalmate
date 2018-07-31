package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Clearance;

import java.util.List;

public interface ClearanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Clearance record);

    int insertSelective(Clearance record);

    Clearance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clearance record);

    int updateByPrimaryKey(Clearance record);

    List<Clearance> selectByNeedId(String needId);
}