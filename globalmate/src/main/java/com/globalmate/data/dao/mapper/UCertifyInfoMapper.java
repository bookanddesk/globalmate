package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.UCertifyInfo;

public interface UCertifyInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UCertifyInfo record);

    int insertSelective(UCertifyInfo record);

    UCertifyInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UCertifyInfo record);

    int updateByPrimaryKey(UCertifyInfo record);
}