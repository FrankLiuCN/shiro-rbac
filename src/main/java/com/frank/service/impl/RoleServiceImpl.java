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
		//��ҳ��ѯ
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles=roleDao.queryRoles(roleName);		
		PageModel<Role> model=new PageModel<Role>(roles, new PageInfo(roles));
		return model;
	}

	@Override
	public int addRole(String roleName) {		
		return roleDao.addRole(roleName);
	}

}
