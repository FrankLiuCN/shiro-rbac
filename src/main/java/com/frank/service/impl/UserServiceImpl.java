package com.frank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.dao.UserDao;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.User;
import com.frank.enums.Status;
import com.frank.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User queryUserByLoginName(String loginName) {
		return userDao.queryUserByLoginName(loginName);
	}

	@Override
	public PageModel<UserModel> queryUsers(int pageNum,int pageSize,String fuzzy) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> users =  userDao.queryUsers(fuzzy);	
		List<UserModel> models=new ArrayList<UserModel>();
		for(User user: users)
		{
			UserModel model=new UserModel(user);
			model.setPassword("");
			models.add(model);
		}
		PageModel<UserModel> pageModel=new PageModel<UserModel>(models,new PageInfo(users));
		return pageModel;
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

	@Override
	public UserModel queryUserByUserID(int userID) {	
		User user=userDao.queryUserByUserID(userID);		
		UserModel model=new UserModel(user);
		model.setPassword("");	
		return model;
	}

	@Override
	public int editUser(User user) {
		return userDao.editUser(user);
	}

	@Override
	public int deleteUser(String[] userIDs) {
		return userDao.deleteUser(userIDs);
	}

}
