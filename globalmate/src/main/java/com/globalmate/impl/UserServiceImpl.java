/**
 * 
 */
package com.globalmate.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import com.globalmate.user.User;
import com.globalmate.user.UserService;

/**
 * @author zhuyjh User
 */
public class UserServiceImpl implements UserService {

	@Override
	public User getUserById(String id) {
		DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user=null;
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			user = userService.getUserById(id);
		} finally {
			sqlSession.close();
		}
		return user;
	}

	@Override
	public int saveUser(User user) {
		DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result=-1;
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			result = userService.saveUser(user);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int updateUser(User user) {
		DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result=-1;
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			result = userService.updateUser(user);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int deleteUser(User user) {
		DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result=-1;
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			result = userService.deleteUser(user);
		} finally {
			sqlSession.close();
		}
		return result;
	}

}
