package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.TProvide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TProvideMapper {
    int deleteByPrimaryKey(String id);

    int insert(TProvide record);

    int insertSelective(TProvide record);

    TProvide selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TProvide record);

    int updateByPrimaryKey(TProvide record);

    List<TProvide> selectProvides(TProvide provide);

}