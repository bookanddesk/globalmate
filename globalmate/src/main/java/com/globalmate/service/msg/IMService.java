package com.globalmate.service.msg;

import com.globalmate.data.dao.mapper.NeedChatRecordMapper;
import com.globalmate.data.entity.NeedChatRecord;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/11/21 13:51
 * @Description
 */
@Service
public class IMService {

    @Autowired
    private NeedChatRecordMapper chatRecordMapper;


    public NeedChatRecord getChatRecord(String recordId) {
        if (StringUtils.isNotEmpty(recordId)) {
            return chatRecordMapper.selectByPrimaryKey(recordId);
        }
        return null;
    }

    public List<NeedChatRecord> getChatRecord(NeedChatRecord record) {
        if (record != null) {
            return chatRecordMapper.selectChatRecords(record);
        }
        return null;
    }

    public NeedChatRecord addChatRecord(NeedChatRecord record) {
        Preconditions.checkNotNull(record);

        List<NeedChatRecord> chatRecords = getChatRecord(
                NeedChatRecord.newBuilder()
                        .setNeedId(record.getNeedId())
                        .setImChatId(record.getImChatId())
                        .setUChatTargetId(record.getuChatTargetId())
                        .build()
        );
        if (CollectionUtils.isNotEmpty(chatRecords)) {
            record.setId(chatRecords.get(0).getId());
            return updateChatRecord(record);
        }

        if (StringUtils.isEmpty(record.getId())) {
            record.setId(IdGenerator.generateId());
        }
        record.setImChatCreateTime(Date.from(Instant.now()));
        int insert = chatRecordMapper.insert(record);
        if (insert > 0) {
            return getChatRecord(record.getId());
        }
        return null;
    }

    private boolean isRecordExist(NeedChatRecord record) {
        return CollectionUtils.isEmpty(getChatRecord(
                NeedChatRecord.newBuilder()
                        .setNeedId(record.getNeedId())
                        .setImChatId(record.getImChatId())
                        .setUChatTargetId(record.getuChatTargetId())
                        .build()
        ));
    }

    public NeedChatRecord updateChatRecord(NeedChatRecord record) {
        Preconditions.checkNotNull(record.getId());
        record.setImChatModifyTime(Date.from(Instant.now()));
        chatRecordMapper.updateByPrimaryKeySelective(record);
        return getChatRecord(record.getId());
    }


}
