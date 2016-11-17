package com.frank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.dto.DataResult;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Role;
import com.frank.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="role",method=RequestMethod.GET)
	public String roleManage() {
		return "role";
	}
	
	@RequestMapping(value="role/list",method=RequestMethod.POST)
	@ResponseBody
	public DataResult<PageModel<Role>> roleList(int pageNum,int pageSize,String fuzzy) {
		PageModel<Role> model=roleService.queryRoles(pageNum, pageSize, fuzzy);
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
	
	@RequestMapping(value="role/add",method=RequestMethod.POST)
	@ResponseBody
	public DataResult<String> addRole(String roleName) {
		Integer re=roleService.addRole(roleName);
		DataResult<String> result = new DataResult<String>(0,"执行成功。",re.toString());		
		return result;
	}
	
}
