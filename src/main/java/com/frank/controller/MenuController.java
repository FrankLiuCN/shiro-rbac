package com.frank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.dao.MenuDao;
import com.frank.dto.DataResult;
import com.frank.dto.MenuModel;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Menu;
import com.frank.exception.AddUserExcetion;
import com.frank.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	MenuService menuService;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu() {
		return "menu";
	}

	@RequestMapping(value = "/menu/list")
	@ResponseBody
	public DataResult<PageModel<MenuModel>> menuList(int pageNum, int pageSize, String fuzzy) {
		PageModel<MenuModel> models = menuService.queryMenus(pageNum, pageSize, fuzzy);
		DataResult<PageModel<MenuModel>> result = new DataResult<PageModel<MenuModel>>();
		if (models.getModels().size() > 0) {
			result.setCode(0);
			result.setData(models);
		} else {
			result.setCode(-1);
			result.setMsg("无菜单信息。");
		}
		return result;
	}
	
	@RequestMapping(value = "/menu/{menuID}/detail", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<Menu> queryMenuByMenuID(@PathVariable("menuID")Integer menuID){		
		Menu menu= menuService.queryMenuByMenuID(menuID);
		DataResult<Menu> result;
		if (null!=menu) {
			result = new DataResult<Menu>(0, "执行成功",menu);
		}else {
			 result = new DataResult<Menu>(-1, "找不到此菜单信息。");
		}
		return result;
	}
	
	@RequestMapping(value = "/menu/add", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> addMenu(Menu menu) {
		menuService.addMenu(menu);
		DataResult<String> result = new DataResult<String>(0, "执行成功");
		return result;
	}
	
	@RequestMapping(value = "/menu/edit", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> editMenu(Menu menu) {
		menuService.editMenu(menu);
		DataResult<String> result = new DataResult<String>(0, "执行成功");
		return result;
	}
	
	@RequestMapping(value = "menu/delete", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> deleteMenu(@RequestParam(value = "menuIDs[]") Integer[] menuIDs) {
		Integer re = menuService.deleteMenu(menuIDs);
		DataResult<String> result;
		if (re <= 0) {
			result = new DataResult<String>(-1, "删除失败。");
		} else {
			result = new DataResult<String>(0, "执行成功。");
		}
		return result;
	}
}
