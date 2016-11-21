package com.frank.service;

import java.util.List;
import java.util.Set;

import com.frank.entity.RoleFunction;

public interface RoleFunctionService {

	List<RoleFunction> queryRoleFunctionByRoleID(Integer roleID);
	
	int deleteRoleFunctionByFunctionID(Integer[] functionID);
	
	int deleteRoleFunctionByRoleID(Integer[] roleID);
	
	void editRolePermission(Integer roleID,List<RoleFunction> roleFunctions);
	
	int addRoleFunction(RoleFunction roleFunction);
	
	Set<String> findPermissionByUserID(Integer userID);
}
