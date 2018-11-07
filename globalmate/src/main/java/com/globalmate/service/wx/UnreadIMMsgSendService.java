package com.globalmate.service.wx;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.NeedCommon;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.po.*;
import com.globalmate.data.entity.vo.AbstractNeed;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.common.ICreateService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

/**
 * @author XingJiajun
 * @Date 2018/10/11 19:30
 * @Description
 */
@Service
public class UnreadIMMsgSendService extends WxTempMsgSendService
        implements IMsgSendService, ICreateService<UnreadIMMsg, UnReadIMEntity> {

    @Autowired
    private UserService userService;
    @Autowired
    private NeedService needService;
    @Value("${unReadMsgId}")
    private String unReadMsgId;
    @Value("${imDetailPage}")
    private String imDetailPage;


    @Override
    String getMsgTemplateId(MsgEntity msgEntity) {
        return unReadMsgId;
    }

    @Override
    public UnreadIMMsg create(UnReadIMEntity unReadIMEntity) {
        if (unReadIMEntity == null) {
            return null;
        }
        String uId = unReadIMEntity.getToUserId();
        if (StringUtils.isEmpty(uId)) {
            return null;
        }
        String openId = userService.getOpenId(uId);
        if (StringUtils.isEmpty(openId)) {
            return null;
        }

        UnreadIMMsg unreadIMMsg = new UnreadIMMsg();
        unreadIMMsg.setToUserId(openId);
        unreadIMMsg.setMsgTempId(getMsgTemplateId(unreadIMMsg));
//        unreadIMMsg.setUrl();
        unreadIMMsg.setFirst(String.format(GMConstant.UNREAD_IM_MSG_FIRST_VALUE,
                unReadIMEntity.getMsgCount() != null ? unReadIMEntity.getMsgCount() + "条" : ""));
        //消息类型
        unreadIMMsg.setKeyword1(GMConstant.UNREAD_IM_MSG_KEYWORD1_VALUE);
        //发送状态
        unreadIMMsg.setKeyword2(GMConstant.UNREAD_IM_MSG_KEYWORD2_VALUE);
        //发送时间
        unreadIMMsg.setKeyword3(DateUtil.format(unReadIMEntity.getMsgSendDate() == null ?
                Date.from(Instant.now()) : unReadIMEntity.getMsgSendDate(), DateUtil.FMT_DATETIME_MINUTE));
        //发送对象
        unreadIMMsg.setKeyword4(userService.getName(unReadIMEntity.getFromUserId()));

        unreadIMMsg.setUrl(String.format(imDetailPage,
                unReadIMEntity.getId(), uId, openId, unReadIMEntity.getToChartId(), unReadIMEntity.isFromDetail()));

        String remark = "邀请您继续在GloHelp的洽谈。";
        NeedAggEntity needAgg = needService.getNeedAgg(unReadIMEntity.getNeedId());
        if (needAgg != null && needAgg.getConceretNeed() != null) {
            AbstractNeed conceretNeed = needAgg.getConceretNeed();
            if (conceretNeed instanceof NeedCommon) {
                String title = ((NeedCommon) conceretNeed).getTitle();
                if (StringUtils.isNotEmpty(title)) {
                    remark = String.format(GMConstant.UNREAD_IM_MSG_REMARK_VALUE, title);
                }
            }
        }
        unreadIMMsg.setRemark(remark);

        return unreadIMMsg;
    }

    public void send(UnReadIMEntity unReadIMEntity) {
        UnreadIMMsg unreadIMMsg = create(unReadIMEntity);
        if (unreadIMMsg != null) {
            send(unreadIMMsg);
        }
    }

}
