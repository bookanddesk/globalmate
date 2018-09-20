package com.globalmate.service.certify;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmate.data.dao.mapper.UCertifyInfoMapper;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.po.GMEnums.UCertifyEffectiveType;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;

@Service
public class UCertifyInfoService implements IUCertifyInfoService {

    @Autowired
    private UCertifyInfoMapper ucertifyInfoMapper;

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
            return ucertifyInfoMapper.selectByPrimaryKey(record.getId());
        }
        return null;
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
            throw new IllegalStateException("email address can't be empty when certify by email!");
        }
        if (!RegexUtils.checkEmail(email)) {
            throw new IllegalStateException("email address is illegal!");
        }
    }

}
