package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.NeedCommon;

import java.util.List;

public interface NeedCommonMapper {
    int deleteByPrimaryKey(String id);

    int insert(NeedCommon record);

    int insertSelective(NeedCommon record);

    NeedCommon selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NeedCommon record);

    int updateByPrimaryKey(NeedCommon record);

    List<NeedCommon> selectByNeedId(String needId);
}