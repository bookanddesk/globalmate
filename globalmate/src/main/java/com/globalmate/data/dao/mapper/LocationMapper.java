package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.Location;

import java.util.List;

public interface LocationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    int insertBatch(List<Location> list);

    List<String> distinctCountries();

    List<Location> queryLike(Location location);
}