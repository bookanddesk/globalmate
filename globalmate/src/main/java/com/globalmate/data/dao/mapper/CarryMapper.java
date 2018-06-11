package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Carry;

public interface CarryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Carry record);

    int insertSelective(Carry record);

    Carry selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Carry record);

    int updateByPrimaryKey(Carry record);
}