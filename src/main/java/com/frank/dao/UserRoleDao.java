package com.frank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.UserRole;

public interface UserRoleDao {
	
	int deleteUserRoleByUserID(Integer[] userID);
	
	int addUserRole(@Param("userID")Integer userID,@Param("roleID")Integer roleID);
	
	List<UserRole> queryUserRole();

}
