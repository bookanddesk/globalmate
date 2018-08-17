package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.UFansRelations;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UFansRelationsMapper {
    int deleteByPrimaryKey(String id);

    int insert(UFansRelations record);

    int insertSelective(UFansRelations record);

    UFansRelations selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UFansRelations record);

    int updateByPrimaryKey(UFansRelations record);

    int relationsCount(@Param("uId") String userId,
                       @Param("uRelatedId") String targetUserId,
                       @Param("relationType") short relationType);

    List<UFansRelations> queryLike(UFansRelations relations);
}