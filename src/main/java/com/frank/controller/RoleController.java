package com.frank.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.dto.DataResult;
import com.frank.dto.PageModel;
import com.frank.dto.RoleFunctionModel;
import com.frank.dto.UserModel;
import com.frank.entity.MenuPermission;
import com.frank.entity.Role;
import com.frank.entity.RoleFunction;
import com.frank.service.MenuService;
import com.frank.service.RoleFunctionService;
import com.frank.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	MenuService menuService;
	
	@Autowired
	RoleFunctionService roleFunctionService;

	@RequiresPermissions("role:view")
	@RequestMapping(value = "role", method = RequestMethod.GET)
	public String roleManage() {
		return "role";
	}

	@RequiresPermissions("role:view")
	@RequestMapping(value = "role/list", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<PageModel<Role>> roleList(int pageNum, int pageSize, String fuzzy) {
		PageModel<Role> model = roleService.queryRoles(pageNum, pageSize, fuzzy);
		DataResult<PageModel<Role>> result = new DataResult<PageModel<Role>>();
		if (model.getModels().size() > 0) {
			result.setCode(0);
			result.setMsg("执行成功。");
			result.setData(model);
		} else {
			result.setCode(-2);
			result.setMsg("无角色信息。");
		}
		return result;
	}

	@RequestMapping(value = "role/all", method = RequestMethod.GET)
	@ResponseBody
	public DataResult<List<Role>> allRoleList() {
		List<Role> roles = roleService.queryAllRoles();
		DataResult<List<Role>> result = new DataResult<List<Role>>();
		result.setCode(0);
		result.setMsg("执行成功。");
		result.setData(roles);
		return result;
	}

	@RequiresPermissions("role:add")
	@RequestMapping(value = "role/add", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> addRole(String roleName) {
		Integer re = roleService.addRole(roleName);
		DataResult<String> result = new DataResult<String>(0, "执行成功。", re.toString());
		return result;
	}

	@RequiresPermissions("role:edit")
	@RequestMapping(value = "role/edit", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> editRole(Role role) {
		Integer re = roleService.editRole(role);
		DataResult<String> result;
		if (re <= 0) {
			result = new DataResult<String>(-1, "编辑失败。");
		} else {
			result = new DataResult<String>(0, "执行成功。", re.toString());
		}
		return result;
	}

	@RequiresPermissions("role:delete")
	@RequestMapping(value = "role/delete", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> deleteRole(@RequestParam(value = "roleIDs[]") int[] roleIDs) {
		Integer re = roleService.deleteRole(roleIDs);
		DataResult<String> result;
		if (re <= 0) {
			result = new DataResult<String>(-1, "删除失败。");
		} else {
			result = new DataResult<String>(0, "执行成功。", re.toString());
		}
		return result;
	}

	@RequiresPermissions("role:setPermission")
	@RequestMapping(value = "role/permission/{roleID}", method = RequestMethod.GET)
	@ResponseBody
	public DataResult<List<MenuPermission>> rolePermission(@PathVariable("roleID") Integer roleID) {
		List<MenuPermission> menuPermissions = menuService.queryMenuPermission(roleID);
		DataResult<List<MenuPermission>> result = new DataResult<List<MenuPermission>>();
		result.setCode(0);
		result.setMsg("执行成功。");
		result.setData(menuPermissions);
		return result;
	}
	
	@RequiresPermissions("role:setPermission")
	@RequestMapping(value = "role/permission/edit", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> editRolePermission(@RequestBody RoleFunctionModel model) {		
		roleFunctionService.editRolePermission(model.getRoleID(), model.getRoleFunctions());
		DataResult<String> result= new DataResult<String>(0, "执行成功。");
		return result;
	}

}
