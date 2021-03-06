package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Buy;

import java.util.List;

public interface BuyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Buy record);

    int insertSelective(Buy record);

    Buy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Buy record);

    int updateByPrimaryKey(Buy record);

    List<Buy> selectByNeedId(String needId);
}