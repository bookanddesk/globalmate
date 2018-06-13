package com.globalmate.user;
/**
 * @author zhuyjh
 * User 
 */
public interface UserService {
	public static final String NAMESPACE = "com.globalmate.mapper.UserMapper";
	
	User getUserById(String id);
	
	int saveUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(User user);

}
