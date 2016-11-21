package com.frank.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.dao.RoleFunctionDao;
import com.frank.entity.RoleFunction;
import com.frank.exception.BaseExcetion;
import com.frank.service.RoleFunctionService;

@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {

	@Autowired
	RoleFunctionDao roleFunctionDao;
	
	@Override
	public List<RoleFunction> queryRoleFunctionByRoleID(Integer roleID) {
		return roleFunctionDao.queryRoleFunctionByRoleID(roleID);
	}

	@Override
	public int deleteRoleFunctionByFunctionID(Integer[] functionID) {
		return roleFunctionDao.deleteRoleFunctionByFunctionID(functionID);
	}
	
	@Override
	public int deleteRoleFunctionByRoleID(Integer[] roleIDs) {	
		return roleFunctionDao.deleteRoleFunctionByRoleID(roleIDs);
	}

	@Override
	@Transactional
	public void editRolePermission(Integer roleID,List<RoleFunction> roleFunctions) {
		try {
			Integer[] roleIDs=new Integer[1];
			roleIDs[0]=roleID;
			deleteRoleFunctionByRoleID(roleIDs);
			for (RoleFunction roleFunction : roleFunctions) {
				addRoleFunction(roleFunction);
			}
		} catch (Exception e) {
			// 把编译期异常 转化为运行期异常，事务才会回滚
			throw new BaseExcetion("编辑权限异常:" + e.getMessage());
		}
	}

	@Override
	public int addRoleFunction(RoleFunction roleFunction) {		
		return roleFunctionDao.addRoleFunction(roleFunction);
	}

	@Override
	public Set<String> findPermissionByUserID(Integer userID) {
		
		return roleFunctionDao.findPermissionByUserID(userID);
	}



}
