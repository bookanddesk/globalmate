package com.globalmate.service.msg;

import com.globalmate.cache.CacheServiceImpl;
import com.globalmate.data.entity.po.UnReadIMEntity;
import com.globalmate.service.wx.UnreadIMMsgSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XingJiajun
 * @Date 2018/11/8 9:11
 * @Description
 */
@Service
public class MsgService {

    @Autowired
    private CacheServiceImpl cacheService;
    @Autowired
    private UnreadIMMsgSendService unreadIMMsgSendService;

    public boolean isSendFrequently(UnReadIMEntity entity) {
        return cacheService.getSerializer(entity.getFromUserId() + entity.getToUserId(), UnReadIMEntity.class) != null;
    }

    public void sendUnreadIMTempMsg(UnReadIMEntity entity) {
        entity.setToChartId(entity.getFromUserId());
        entity.setId(entity.getNeedId());
        unreadIMMsgSendService.send(entity);
        cacheService.setSerializer(entity.getFromUserId() + entity.getToUserId(), entity, 3600L);
    }

}
