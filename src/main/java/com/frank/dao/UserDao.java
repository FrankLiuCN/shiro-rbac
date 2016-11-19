package com.frank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.User;

public interface UserDao {
	User queryUserByLoginName(String loginName);
	
	List<User> queryUsers(@Param("fuzzy")String fuzzy); 
	
	void updateLastLoginTime(String loginName);
	
	int addUser(User user);
	
	User queryUserByUserID(@Param("userID")int userID);
	
	int editUser(User user);
	
	int deleteUser(Integer[] userIDs);
}
