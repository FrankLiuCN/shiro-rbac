package com.frank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.dao.UserRoleDao;
import com.frank.entity.UserRole;
import com.frank.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public int deleteUserRoleByUserID(Integer[] userIDs) {	
		return userRoleDao.deleteUserRoleByUserID(userIDs);
	}

	@Override
	public int addUserRole(Integer userID, Integer roleID) {
		return userRoleDao.addUserRole(userID, roleID);
	}

	@Override
	public List<UserRole> queryUserRole() {		
		return userRoleDao.queryUserRole();
	}
	

}
