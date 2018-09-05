package com.globalmate.data.dao.mapper;

import java.util.List;

import com.globalmate.data.entity.Need;
import org.apache.ibatis.annotations.Param;

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

    int updateNeedEnable(@Param("needId") String needId, @Param("enableStr") String enableStr);

    List<Need> associatedQuery(@Param("need") Need need, @Param("keyword") String keyword);
}