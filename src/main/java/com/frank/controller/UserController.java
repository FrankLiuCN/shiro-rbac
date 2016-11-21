package com.frank.controller;

import java.util.List;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.dao.UserDao;
import com.frank.dto.DataResult;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.User;
import com.frank.exception.AddUserExcetion;
import com.frank.service.UserService;
import com.frank.shiro.SubjectManager;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> loginSubmit(String loginName, String password, boolean rememberMe) {
		DataResult<String> result = new DataResult<String>();
		SubjectManager.login(loginName, password, rememberMe);
		result.setCode(0);
		result.setData("/main");
		return result;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		SubjectManager.logout();
		return "redirect:login";
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userManage() {
		return "user";
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = "/user/list", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<PageModel<UserModel>> userList(int pageNum,int pageSize,String fuzzy) {
		PageModel<UserModel> models = userService.queryUsers(pageNum,pageSize,fuzzy);
		DataResult<PageModel<UserModel>> result = new DataResult<PageModel<UserModel>>();
		if (models.getModels().size() > 0) {
			result.setCode(0);
			result.setData(models);
		} else {
			result.setCode(-1);
			result.setMsg("无用户信息。");
		}
		return result;
	}

	@RequiresPermissions("user:add")
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> addUser(@RequestBody UserModel userModel) {
		DataResult<String> result;
		try {
			userService.addUser(userModel);
			result=new DataResult<String>(0,"执行成功");
		} catch (AddUserExcetion aue) {
			result=new DataResult<String>(-1,aue.getMessage());
		}
		return result;
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> editUser(@RequestBody UserModel userModel) {
		userService.editUser(userModel);
		DataResult<String> result=new DataResult<String>(0,"执行成功","");
		return result;
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> deleteUser(@RequestParam(value = "userIDs[]") Integer[] userIDs) {
		Integer re=userService.deleteUser(userIDs);
		DataResult<String> result=new DataResult<String>(0,"执行成功");
		return result;
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/user/{userID}/detail", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<UserModel> userDetail(@PathVariable("userID")int userID){
		UserModel model=userService.queryUserByUserID(userID);
		DataResult<UserModel> result=new DataResult<UserModel>(0,"执行成功",model);
		return result;
	}
}
