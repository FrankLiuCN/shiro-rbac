package com.frank.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.entity.User;
import com.frank.service.UserService;

@Service
public class MyRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String userName = (String) principalCollection.fromRealm(getName()).iterator().next();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		ShiroToken token = (ShiroToken) authenticationToken;
		String loginName = token.getUsername();
		User user = userService.queryUserByLoginName(loginName);
		if (null == user || !token.getPswd().equals(user.getPassword())) {
			throw new AccountException("帐号或密码不正确！");
		}
		if (1 != user.getStatus()) {
			throw new DisabledAccountException("账号已经禁止登录!");
		}
		userService.updateLastLoginTime(loginName);
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

}