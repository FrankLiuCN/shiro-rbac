package com.frank.service;

import java.util.List;

import com.frank.dto.UserModel;
import com.frank.entity.User;

public interface UserService {
	User queryUserByLoginName(String loginName);
	
	List<UserModel> queryUsers(); 
	
	void updateLastLoginTime(String loginName);
	
	int addUser(UserModel model);
}
