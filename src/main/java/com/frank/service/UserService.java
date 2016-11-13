package com.frank.service;

import java.util.List;

import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.User;

public interface UserService {
	User queryUserByLoginName(String loginName);
	
	PageModel<UserModel> queryUsers(int pageNum,int pageSize); 
	
	void updateLastLoginTime(String loginName);
	
	int addUser(UserModel model);
}
