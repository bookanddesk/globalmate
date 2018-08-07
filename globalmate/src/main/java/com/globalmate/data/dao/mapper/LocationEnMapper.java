package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.LocationEn;

import java.util.List;

public interface LocationEnMapper {
    int deleteByPrimaryKey(String id);

    int insert(LocationEn record);

    int insertSelective(LocationEn record);

    LocationEn selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LocationEn record);

    int updateByPrimaryKey(LocationEn record);

    int insertBatch(List<LocationEn> locationEns);
}