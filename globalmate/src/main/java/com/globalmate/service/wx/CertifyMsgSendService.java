package com.globalmate.service.wx;

import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.po.CertifyMsg;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.po.MsgEntity;
import com.globalmate.service.common.ICreateService;
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
public class CertifyMsgSendService extends WxTempMsgSendService
        implements IMsgSendService, ICreateService<CertifyMsg, UCertifyInfo> {

    @Autowired
    private UserService userService;
    @Value("${certifyMsgId}")
    private String certifyMsgId;

    @Override
    String getMsgTemplateId(MsgEntity msgEntity) {
        return certifyMsgId;
    }


    @Override
    public CertifyMsg create(UCertifyInfo certifyInfo) {
        if (certifyInfo == null) {
            return null;
        }
        String uId = certifyInfo.getuId();
        if (StringUtils.isEmpty(uId)) {
            return null;
        }
        String openId = userService.getOpenId(uId);
        if (StringUtils.isEmpty(openId)) {
            return null;
        }

        String certifyType = GMEnums.UCertifyType.valueOf(certifyInfo.getCetifyType()).getShowValue();
        boolean certifyResult = GMEnums.UCertifyEffectiveType.PASS.getValue() == certifyInfo.getIsEffective();
        CertifyMsg certifyMsg = new CertifyMsg();
        certifyMsg.setToUserId(openId);
        certifyMsg.setMsgTempId(getMsgTemplateId(certifyMsg));
//        certifyMsg.setUrl();
        certifyMsg.setFirst(GMConstant.CERTIFY_MSG_FIRST_VALUE);
        //认证状态
        certifyMsg.setKeyword1(certifyResult ? GMConstant.CERTIFY_MSG_PASS : GMConstant.CERTIFY_MSG_NOT_PASS);
        //认证类型
        certifyMsg.setKeyword2(certifyType);
        //认证信息
        certifyMsg.setKeyword3(String.format(GMConstant.CERTIFY_MSG_CERTIFY_INFO, userService.getName(uId)));
        //处理时间
        certifyMsg.setKeyword4(DateUtil.format(Date.from(Instant.now()), DateUtil.FMT_DATETIME_MINUTE));

        certifyMsg.setRemark(certifyResult ? GMConstant.CERTIFY_MSG_REMARK_PASS : GMConstant.CERTIFY_MSG_REMARK_NOT_PASS);

        return certifyMsg;
    }

    public void send(UCertifyInfo certifyInfo) {
        CertifyMsg certifyMsg = create(certifyInfo);
        if (certifyMsg != null) {
            send(certifyMsg);
        }
    }
}
