package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.SysMatchNeed;

import java.util.List;

public interface SysMatchNeedMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMatchNeed record);

    int insertSelective(SysMatchNeed record);

    SysMatchNeed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMatchNeed record);

    int updateByPrimaryKey(SysMatchNeed record);

    List<SysMatchNeed> queryMatchNeeds(SysMatchNeed record);

    int updateMatchAccept(List<String> ids, boolean agree);

}