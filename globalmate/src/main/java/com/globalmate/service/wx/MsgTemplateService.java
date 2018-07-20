package com.globalmate.service.wx;

import com.globalmate.cache.LocalCache;
import com.globalmate.data.dao.mapper.WxMsgTemplateMapper;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XingJiajun
 * @Date 2018/7/20 11:09
 * @Description
 */

@Service
public class MsgTemplateService {

    @Autowired
    private WxMsgTemplateMapper msgTemplateMapper;

    private static final LocalCache<String, String> tempCache = new LocalCache<>();

    public String getTemplateId(String msgType) {
        return getTempldatId(msgType, null);
    }

    public String getTempldatId(String msgType, String appId) {
        if (msgType == null) {
            return null;
        }
        String key = StringUtils.join_(msgType, appId),
                tempId = tempCache.get(key);
        if (StringUtils.isNotBlank(tempId)) {
            return tempId;
        }
        tempId = msgTemplateMapper.selectTemplateId(msgType, appId);
        if (StringUtils.isNotBlank(tempId)) {
            tempCache.put(key, tempId);
        }
        return tempId;
    }

}
