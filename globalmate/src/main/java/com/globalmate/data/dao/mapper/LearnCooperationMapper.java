package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.LearnCooperation;

import java.util.List;

public interface LearnCooperationMapper {
    int deleteByPrimaryKey(String id);

    int insert(LearnCooperation record);

    int insertSelective(LearnCooperation record);

    LearnCooperation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LearnCooperation record);

    int updateByPrimaryKey(LearnCooperation record);

    List<LearnCooperation> selectByNeedId(String needId);
}