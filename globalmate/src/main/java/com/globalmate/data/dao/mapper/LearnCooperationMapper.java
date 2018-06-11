package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.LearnCooperation;

public interface LearnCooperationMapper {
    int deleteByPrimaryKey(String id);

    int insert(LearnCooperation record);

    int insertSelective(LearnCooperation record);

    LearnCooperation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LearnCooperation record);

    int updateByPrimaryKey(LearnCooperation record);
}