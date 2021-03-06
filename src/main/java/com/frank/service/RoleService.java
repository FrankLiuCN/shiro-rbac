package com.frank.service;

import java.util.List;

import com.frank.dto.PageModel;
import com.frank.entity.Role;

public interface RoleService {
	
	PageModel<Role> queryRoles(int pageNum,int pageSize,String roleName);
	
	int addRole(String roleName);
	
	int editRole(Role role);
	
	int deleteRole(int[] roleIDs);
	
	List<Role> queryAllRoles();
	
	List<Role> queryRoleByUserID(int userID);
}
