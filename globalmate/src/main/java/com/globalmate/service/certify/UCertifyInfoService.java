package com.globalmate.service.certify;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalmate.data.dao.mapper.UCertifyInfoMapper;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.po.GMEnums.UCertifyEffectiveType;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.RegexUtils;
import com.globalmate.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UCertifyInfoService implements IUCertifyInfoService {

    @Autowired
    private UCertifyInfoMapper ucertifyInfoMapper;
    @Autowired
    private UserService userService;

    @Override
    public UCertifyInfo addUCertifyInfo(User user, UCertifyInfo ucertifyInfo) {
        checkNotNull(ucertifyInfo);

        checkCertifyEmail(ucertifyInfo);

        if (StringUtils.isNotBlank(ucertifyInfo.getId())) {
            return this.updateUCertifyInfo(ucertifyInfo);
        }

        ucertifyInfo.setId(IdGenerator.generateId());
        if (ucertifyInfo.getuId() == null) {
            ucertifyInfo.setuId(user.getId());
        }
        if (ucertifyInfo.getuName() == null) {
            ucertifyInfo.setuName(user.getNikename() == null ? user.getName() : user.getNikename());
        }
        if (ucertifyInfo.getCetifyType() == null) {
            //默认身份证认证方式
            ucertifyInfo.setCetifyType(GMEnums.UCertifyType.IDCARD.name());
        }

        if (ucertifyInfo.getIsEffective() != GMEnums.UCertifyEffectiveType.PASS.getValue() && ucertifyInfo.getIsEffective() != GMEnums.UCertifyEffectiveType.NOTPASS.getValue()) {
            //默认为未验证状态
            ucertifyInfo.setIsEffective(GMEnums.UCertifyEffectiveType.UNCHECKED.getValue());
        }

        ucertifyInfo.setCerExt3(GMConstant.ZERO_STR_VALUE);

        ucertifyInfo.setCertifyTime(Date.from(Instant.now()));
        int i = ucertifyInfoMapper.insert(ucertifyInfo);
        if (i > 0) {
            return ucertifyInfoMapper.selectByPrimaryKey(ucertifyInfo.getId());
        }
        return null;
    }

    @Override
    public List<UCertifyInfo> listCertifyInfo(UCertifyInfo certifyInfo) {
        return ucertifyInfoMapper.queryRecords(Optional.ofNullable(certifyInfo).orElse(new UCertifyInfo()));
    }

    @Override
    public UCertifyInfo updateUCertifyInfo(UCertifyInfo record) {
        checkNotNull(record);
        record.setModifyTime(Date.from(Instant.now()));

        if (UCertifyEffectiveType.NOTPASS.getValue() == record.getIsEffective()) {
            record.setCerExt3(GMConstant.ONE_STR_VALUE);
        }

        int i = ucertifyInfoMapper.updateByPrimaryKeySelective(record);
        if (i > 0) {
            record = ucertifyInfoMapper.selectByPrimaryKey(record.getId());
            if (UCertifyEffectiveType.PASS.getValue() == record.getIsEffective()) {
                updateVTag(record);
            }
            return record;
        }
        return null;
    }

    private void updateVTag(UCertifyInfo record) {
        //认证的用户就是金牌 2018-11-05
        GMEnums.vTag vTag = GMEnums.vTag.vGold;
//        GMEnums.vTag vTag = null;
//        try {
//            GMEnums.UCertifyType uCertifyType = GMEnums.UCertifyType.valueOf(record.getCetifyType());
//            vTag = GMEnums.vTag.valueOf(uCertifyType.getVTag());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//        if (vTag == null) {
//            return;
//        }
        String userTag = userService.getUserTag(record.getuId());
        try {
            if (StringUtils.isEmpty(userTag)
                    || vTag.compareTo(GMEnums.vTag.valueOf(userTag)) < 0) {
                userService.updateUserTag(record.getuId(), vTag.name());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UCertifyInfo getUCertifyInfo(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return ucertifyInfoMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    private void checkCertifyEmail(UCertifyInfo info) {
        if (!GMEnums.UCertifyType.EMAIL.name().equalsIgnoreCase(info.getCetifyType())) {
            return;
        }
        String email = info.getCerExt2();
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email address can't be empty when certify by email!");
        }
        if (!RegexUtils.checkEmail(email)) {
            throw new IllegalArgumentException("email address is illegal!");
        }
    }

}
