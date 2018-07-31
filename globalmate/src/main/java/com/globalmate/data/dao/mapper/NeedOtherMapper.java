package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.NeedOther;

import java.util.List;

public interface NeedOtherMapper {
    int deleteByPrimaryKey(String id);

    int insert(NeedOther record);

    int insertSelective(NeedOther record);

    NeedOther selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NeedOther record);

    int updateByPrimaryKey(NeedOther record);

    List<NeedOther> selectByNeedId(String needId);
}