package com.frank.dao;

import java.util.List;

import com.frank.entity.User;

public interface UserDao {
	User queryUserByLoginName(String loginName);
	
	List<User> queryUsers(); 
	
	void updateLastLoginTime(String loginName);
	
	int addUser(User user);
}
