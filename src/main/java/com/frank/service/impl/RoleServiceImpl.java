package com.frank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.dao.RoleDao;
import com.frank.dto.PageModel;
import com.frank.entity.Role;
import com.frank.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Override
	public PageModel<Role> queryRoles(int pageNum,int pageSize,String roleName) {
		//∑÷“≥≤È—Ø
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles=roleDao.queryRoles(roleName);		
		PageModel<Role> model=new PageModel<Role>(roles, new PageInfo(roles));
		return model;
	}

	@Override
	public int addRole(String roleName) {		
		return roleDao.addRole(roleName);
	}

	@Override
	public int editRole(Role role) {
		return roleDao.editRole(role);
	}

	@Override
	public int deleteRole(int[] roleIDs) {
		return roleDao.deleteRole(roleIDs);
	}

	@Override
	public List<Role> queryAllRoles() {		
		return roleDao.queryAllRoles();
	}

	@Override
	public List<Role> queryRoleByUserID(int userID) {
		return roleDao.queryRoleByUserID(userID);
	}

}
