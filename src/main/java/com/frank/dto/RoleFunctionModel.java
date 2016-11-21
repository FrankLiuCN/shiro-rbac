package com.frank.dto;

import java.util.List;

import com.frank.entity.RoleFunction;

public class RoleFunctionModel {
	
	private Integer roleID;
	
	List<RoleFunction> roleFunctions;

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public List<RoleFunction> getRoleFunctions() {
		return roleFunctions;
	}

	public void setRoleFunctions(List<RoleFunction> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
			
}
