package com.globalmate.data.dao.mapper;

import java.util.List;

import com.globalmate.data.entity.Need;

public interface NeedMapper {
    int deleteByPrimaryKey(String id);

    int insert(Need record);

    int insertSelective(Need record);

    Need selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Need record);

    int updateByPrimaryKey(Need record);
    
    List<Need> selectNeeds(Need need);

    List<Need> queryNeeds(Need need);

    List<Need> queryByIds(List<String> ids);

    int updateNeedEnable(String needId, String enableStr);
}