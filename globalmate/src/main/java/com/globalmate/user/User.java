package com.globalmate.user;

import java.util.Date;

/**
 * @author zhuyjh
 * User 
 */

public interface User {
	String getId() ;
	void setId(String id) ;
	String getCode() ;
	void setCode(String code);
	String getName();
	void setName(String name);
	String getNickName() ;
	void setNickName(String nickName) ;
	String getPassword() ;
	void setPassword(String password) ;
	String getSalt() ;
	void setSalt(String salt);
	String getEmail() ;
	void setEmail(String email);
	String getPhone();
	void setPhone(String phone) ;
	String getPic();
	void setPic(String pic) ;
	Date getCreateTime() ;
	void setCreateTime(Date createTime);
	Date getModifyTime() ;
	void setModifyTime(Date modifyTime) ;
	boolean isEnable() ;
	void setEnable(boolean enable);
	String getIdNumber();
	void setIdNumber(String idNumber);
	String getNice();
	void setNice(String nice);
	String getWhere() ;
	void setWhere(String where) ;
	String getSchool();
	void setSchool(String school);
	String getHobby();
	void setHobby(String hobby) ;
}
