package com.frank.service;

import java.util.List;

import com.frank.dto.MenuModel;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Menu;

public interface MenuService {
	Menu queryMenuByMenuID(Integer menuID);
	
	PageModel<MenuModel> queryMenus(int pageNum, int pageSize, String fuzzy); 
		
	int addMenu(Menu menu);
	
	int editMenu(Menu menu);
	
	int deleteMenu(Integer[] menuIDs);
}
