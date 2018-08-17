package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.SysSchool;

import java.util.List;

public interface SysSchoolMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysSchool record);

    int insertSelective(SysSchool record);

    SysSchool selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysSchool record);

    int updateByPrimaryKey(SysSchool record);

    List<SysSchool> queryLike(SysSchool sysSchool);
}