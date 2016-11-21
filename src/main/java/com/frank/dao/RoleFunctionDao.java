package com.frank.dao;

import java.util.List;
import java.util.Set;

import com.frank.entity.RoleFunction;

public interface RoleFunctionDao {

	List<RoleFunction> queryRoleFunctionByRoleID(Integer roleID);
	
	int deleteRoleFunctionByFunctionID(Integer[] functionID);
	
	int deleteRoleFunctionByRoleID(Integer[] roleID);
	
	int addRoleFunction(RoleFunction roleFunction);
	
	Set<String> findPermissionByUserID(Integer userID);
}
