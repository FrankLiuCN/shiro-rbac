package com.frank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.Menu;

public interface MenuDao {
	
	Menu queryMenuByMenuID(@Param("menuID")Integer menuID);
	
	List<Menu> queryMenus(@Param("fuzzy")String fuzzy); 
		
	int addMenu(Menu menu);
	
	int editMenu(Menu menu);
	
	int deleteMenu(Integer[] menuIDs);
}
