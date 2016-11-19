package com.frank.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.UserRole;

public interface UserRoleService {
	
	int deleteUserRoleByUserID(Integer[] userID);
	
	int addUserRole(Integer userID,Integer roleID);
	
	List<UserRole> queryUserRole();
}
