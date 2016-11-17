package com.frank.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.User;

public interface UserService {
	User queryUserByLoginName(String loginName);
	
	PageModel<UserModel> queryUsers(int pageNum,int pageSize,String fuzzy); 
	
	void updateLastLoginTime(String loginName);
	
	int addUser(UserModel model);
	
	UserModel queryUserByUserID(@Param("userID")int userID);
	
	int editUser(User user);
	
	int deleteUser(String[] userIDs);
}
