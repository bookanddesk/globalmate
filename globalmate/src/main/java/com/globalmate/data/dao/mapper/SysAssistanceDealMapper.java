package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.SysAssistanceDeal;

import java.util.List;

public interface SysAssistanceDealMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysAssistanceDeal record);

    int insertSelective(SysAssistanceDeal record);

    SysAssistanceDeal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysAssistanceDeal record);

    int updateByPrimaryKey(SysAssistanceDeal record);

    List<SysAssistanceDeal> queryRecords(SysAssistanceDeal assistanceDeal);
}