package com.frank.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.dao.FunctionDao;
import com.frank.dao.MenuDao;
import com.frank.dto.MenuModel;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Function;
import com.frank.entity.Menu;
import com.frank.entity.MenuPermission;
import com.frank.entity.Permission;
import com.frank.entity.RoleFunction;
import com.frank.exception.BaseExcetion;
import com.frank.service.FunctionService;
import com.frank.service.RoleFunctionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl implements com.frank.service.MenuService {

	@Autowired
	MenuDao menuDao;

	@Autowired
	FunctionService functionService;
	
	@Autowired
	RoleFunctionService roleFunctionService;

	@Override
	public Menu queryMenuByMenuID(Integer menuID) {
		return menuDao.queryMenuByMenuID(menuID);
	}

	@Override
	public PageModel<MenuModel> queryMenus(int pageNum, int pageSize, String fuzzy) {
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> menus = menuDao.queryMenus(fuzzy);
		List<MenuModel> menuModels = new ArrayList<MenuModel>();
		for (Menu menu : menus) {
			MenuModel menuModel = new MenuModel(menu);
			menuModels.add(menuModel);
		}
		PageModel<MenuModel> pageModel = new PageModel<MenuModel>(menuModels, new PageInfo(menus));
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
	@Transactional
	public void deleteMenu(Integer[] menuIDs) {
		try {
			//删除菜单的相应方法
			functionService.deleteFunctionByMenuID(menuIDs);
			menuDao.deleteMenu(menuIDs);
		} catch (Exception e) {
			// 把编译期异常 转化为运行期异常，事务才会回滚
			throw new BaseExcetion("删除菜单异常:" + e.getMessage());
		}
	}

	@Override
	public List<MenuPermission> queryMenuPermission(Integer roleID) {	
		List<Menu> menus = menuDao.queryMenus(null);
		List<RoleFunction> roleFunctions= roleFunctionService.queryRoleFunctionByRoleID(roleID);				
		List<MenuPermission> menuPemissions=new ArrayList<MenuPermission>();
		for (Menu menu : menus) {
			MenuPermission menuPermission=new MenuPermission();
			List<Function> functions= functionService.queryFunctionByMenuID(menu.getMenuID());
			for (Function function : functions) {
				Permission permission=new Permission();
				permission.setFunction(function);
				permission.setAllow(false);
				for (RoleFunction roleFunction : roleFunctions) {
					if (function.getFunctionID()==roleFunction.getFunctionID()) {
						permission.setAllow(true);
						break;
					}
				}
				menuPermission.getPermissions().add(permission);
			}			
			menuPermission.setMenuName(menu.getMenuName());
			menuPemissions.add(menuPermission);
		}

		return menuPemissions;
	}

	
}
