package com.globalmate.service.certify;

import java.util.List;

import com.globalmate.data.entity.UCertifyInfo;
import com.globalmate.data.entity.User;

public interface IUCertifyInfoService {
	
	//增加
	UCertifyInfo addUCertifyInfo(User user, UCertifyInfo record);
	//查询
    public List<UCertifyInfo> listCertifyInfo(UCertifyInfo certifyInfo);
	//更新
    UCertifyInfo updateUCertifyInfo(UCertifyInfo record);

}
