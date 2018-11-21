package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.NeedChatRecord;

import java.util.List;

public interface NeedChatRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(NeedChatRecord record);

    int insertSelective(NeedChatRecord record);

    NeedChatRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NeedChatRecord record);

    int updateByPrimaryKey(NeedChatRecord record);

    List<NeedChatRecord> selectChatRecords(NeedChatRecord record);
}