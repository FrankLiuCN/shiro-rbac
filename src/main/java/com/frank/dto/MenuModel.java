package com.frank.dto;

import com.frank.entity.Menu;
import com.frank.enums.Status;

public class MenuModel {
	
	private Integer menuID;
	
	private String menuName;
	
	private Integer sort;
	
	private String href;
	
	private Integer status;
	
	private String statusName;
	
	private String remark;

	public MenuModel() {
		
	}
	public MenuModel(Menu menu) {
		menuID=menu.getMenuID();
		menuName=menu.getMenuName();
		sort=menu.getSort();
		href=menu.getHref();
		status=menu.getStatus();
		statusName=Status.stateOf(menu.getStatus()).getName();
		remark=menu.getRemark();
	}
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}
