package com.frank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.dao.UserDao;
import com.frank.dto.UserModel;
import com.frank.entity.User;
import com.frank.enums.Status;
import com.frank.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User queryUserByLoginName(String loginName) {
		return userDao.queryUserByLoginName(loginName);
	}

	@Override
	public List<UserModel> queryUsers() {
		List<User> users =  userDao.queryUsers();
		List<UserModel> models=new ArrayList<UserModel>();
		for(User user: users)
		{
			UserModel model=new UserModel();
			model.setNickName(user.getNickName());
			model.setLoginName(user.getLoginName());
			model.setStatus(user.getStatus());
			model.setStatusName(Status.stateOf(user.getStatus()).getName());
			model.setCreateTime(user.getCreateTime());
			model.setLastLoginTime(user.getLastLoginTime());
			models.add(model);
		}
		return models;
	}

	@Override
	public void updateLastLoginTime(String loginName) {
		userDao.updateLastLoginTime(loginName);
	}

	@Override
	public int addUser(UserModel model) {
		User user=new User();
		user.setNickName(model.getNickName());
		user.setLoginName(model.getLoginName());
		user.setPassword(model.getPassword());
		user.setStatus(model.getStatus());
		return userDao.addUser(user);
	}

}
