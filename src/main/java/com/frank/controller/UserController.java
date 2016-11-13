package com.frank.controller;

import java.util.List;

import javax.jws.soap.InitParam;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.dto.DataResult;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.User;
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

		return "redirect:login";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userManage() {
		return "user";
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<PageModel<UserModel>> userList(int pageNum,int pageSize) {
		PageModel<UserModel> models = userService.queryUsers(pageNum,pageSize);
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

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> addUser(UserModel userModel) {
		Integer re=userService.addUser(userModel);
		DataResult<String> result=new DataResult<String>(0,"执行成功",re.toString());
		return result;
	}
}
