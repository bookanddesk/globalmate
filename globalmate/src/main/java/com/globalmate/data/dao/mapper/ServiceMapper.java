package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Service;

public interface ServiceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
}