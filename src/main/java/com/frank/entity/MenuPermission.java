package com.frank.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuPermission {

	private String MenuName;
	
	private List<Permission> permissions;
	

	public MenuPermission() {
		permissions=new ArrayList<Permission>();
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}	
	
}
