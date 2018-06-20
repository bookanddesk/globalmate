package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.TNeedComments;

public interface TNeedCommentsMapper {
    int deleteByPrimaryKey(String id);

    int insert(TNeedComments record);

    int insertSelective(TNeedComments record);

    TNeedComments selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TNeedComments record);

    int updateByPrimaryKey(TNeedComments record);
}