package com.frank.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.dao.MenuDao;
import com.frank.dto.MenuModel;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Menu;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl implements com.frank.service.MenuService {

	@Autowired
	MenuDao menuDao;
	
	@Override
	public Menu queryMenuByMenuID(Integer menuID) {		
		return menuDao.queryMenuByMenuID(menuID);
	}

	@Override
	public PageModel<MenuModel> queryMenus(int pageNum, int pageSize, String fuzzy) {
		PageHelper.startPage(pageNum,pageSize);
		List<Menu> menus= menuDao.queryMenus(fuzzy);
		List<MenuModel> menuModels=new ArrayList<MenuModel>();
		for (Menu menu : menus) {
			MenuModel menuModel=new MenuModel(menu);
			menuModels.add(menuModel);
		}		
		PageModel<MenuModel> pageModel=new PageModel<MenuModel>(menuModels, new PageInfo(menus));
		return pageModel;
	}

	@Override
	public int addMenu(Menu menu) {	
		return menuDao.addMenu(menu);
	}

	@Override
	public int editMenu(Menu menu) {	
		return menuDao.editMenu(menu);
	}

	@Override
	public int deleteMenu(Integer[] menuIDs) {
		return menuDao.deleteMenu(menuIDs);
	}

}
