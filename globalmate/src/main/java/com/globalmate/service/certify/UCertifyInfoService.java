package com.globalmate.service.certify;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmate.data.dao.mapper.UCertifyInfoMapper;
import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.uitl.IdGenerator;

@Service
public class UCertifyInfoService implements IUCertifyInfoService{
	
	 @Autowired
	 private UCertifyInfoMapper ucertifyInfoMapper;

	@Override
	public UCertifyInfo addUCertifyInfo(User user, UCertifyInfo ucertifyInfo) {
		checkNotNull(ucertifyInfo);
        if (ucertifyInfo.getId() == null) {
        	ucertifyInfo.setId(IdGenerator.generateId());
        }
        if (ucertifyInfo.getuId() == null) {
        	ucertifyInfo.setuId(user.getId());
        }
        if (ucertifyInfo.getuName() == null) {
        	ucertifyInfo.setuName(user.getName());
        }
        if (ucertifyInfo.getCetifyType() == null) {
        	//默认身份证认证方式
        	ucertifyInfo.setCetifyType(GMEnums.UCertifyType.IDCARD.name());
        }
        ucertifyInfo.setCertifyTime(Date.from(Instant.now()));
        int i = ucertifyInfoMapper.insert(ucertifyInfo);
        if (i > 0) {
        	updateUCertifyInfo(ucertifyInfo);
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
        int i = ucertifyInfoMapper.updateByPrimaryKeySelective(record);
        if (i > 0) {
            return ucertifyInfoMapper.selectByPrimaryKey(record.getId());
        }
        return null;
	}

}
