package com.frank.shiro;

import org.apache.shiro.SecurityUtils;

import com.frank.entity.User;

public class SubjectManager {

	/**
	 * 获取当前登录的用户User对象
	 * 
	 * @return
	 */
	public static User getToken() {
		User token = (User) SecurityUtils.getSubject().getPrincipal();
		return token;
	}

	public static void login(String loginName, String password, Boolean rememberMe) {
		ShiroToken token = new ShiroToken(loginName, password);
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);
	}
	
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
}
