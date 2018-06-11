package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.UCredit;

public interface UCreditMapper {
    int deleteByPrimaryKey(String id);

    int insert(UCredit record);

    int insertSelective(UCredit record);

    UCredit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UCredit record);

    int updateByPrimaryKey(UCredit record);
}