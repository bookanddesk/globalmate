package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.UTag;

public interface UTagMapper {
    int deleteByPrimaryKey(String id);

    int insert(UTag record);

    int insertSelective(UTag record);

    UTag selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UTag record);

    int updateByPrimaryKey(UTag record);
}