package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.UEvaluation;

import java.util.List;

public interface UEvaluationMapper {
    int deleteByPrimaryKey(String id);

    int insert(UEvaluation record);

    int insertSelective(UEvaluation record);

    UEvaluation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UEvaluation record);

    int updateByPrimaryKey(UEvaluation record);

    List<UEvaluation> queryRecords(UEvaluation evaluation);
}