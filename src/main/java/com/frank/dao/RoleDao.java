package com.frank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.Role;

public interface RoleDao {
	
	List<Role> queryRoles(@Param("roleName")String roleName);
	
	int addRole(String roleName);
	
	int editRole(Role role);
	
	int deleteRole(int[] roleIDs);
	
	List<Role> queryAllRoles();
	
	List<Role> queryRoleByUserID(int userID);
}
