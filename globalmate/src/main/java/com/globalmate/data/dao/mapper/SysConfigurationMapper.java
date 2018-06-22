package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.SysConfiguration;

public interface SysConfigurationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysConfiguration record);

    int insertSelective(SysConfiguration record);

    SysConfiguration selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysConfiguration record);

    int updateByPrimaryKey(SysConfiguration record);
}