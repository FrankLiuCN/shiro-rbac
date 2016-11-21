package com.frank.service;

import java.util.List;

import com.frank.dto.MenuModel;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Menu;
import com.frank.entity.MenuPermission;

public interface MenuService {
	Menu queryMenuByMenuID(Integer menuID);
	
	PageModel<MenuModel> queryMenus(int pageNum, int pageSize, String fuzzy); 
		
	int addMenu(Menu menu);
	
	int editMenu(Menu menu);
	
	void deleteMenu(Integer[] menuIDs);
	
	List<MenuPermission> queryMenuPermission(Integer roleID);
}
