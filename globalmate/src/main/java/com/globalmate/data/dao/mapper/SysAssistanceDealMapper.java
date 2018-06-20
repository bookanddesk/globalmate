package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.SysAssistanceDeal;

public interface SysAssistanceDealMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysAssistanceDeal record);

    int insertSelective(SysAssistanceDeal record);

    SysAssistanceDeal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysAssistanceDeal record);

    int updateByPrimaryKey(SysAssistanceDeal record);
}