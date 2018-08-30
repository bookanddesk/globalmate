package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.LocationCnEn;

public interface LocationCnEnMapper {
    int deleteByPrimaryKey(String id);

    int insert(LocationCnEn record);

    int insertSelective(LocationCnEn record);

    LocationCnEn selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LocationCnEn record);

    int updateByPrimaryKey(LocationCnEn record);

    LocationCnEn selectByCountry(String country);

    LocationCnEn selectByCity(String city);
}