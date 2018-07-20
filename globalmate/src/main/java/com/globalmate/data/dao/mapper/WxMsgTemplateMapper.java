package com.globalmate.data.dao.mapper;

import com.globalmate.data.entity.WxMsgTemplate;
import org.apache.ibatis.annotations.Param;

public interface WxMsgTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxMsgTemplate record);

    int insertSelective(WxMsgTemplate record);

    WxMsgTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxMsgTemplate record);

    int updateByPrimaryKey(WxMsgTemplate record);

    String selectTemplateId(@Param("msgType") String msgType,
                            @Param("appId") String appId);
}