package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Accompany;

public interface AccompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Accompany record);

    int insertSelective(Accompany record);

    Accompany selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Accompany record);

    int updateByPrimaryKey(Accompany record);
}