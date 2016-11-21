package com.frank.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.dao.FunctionDao;
import com.frank.dao.RoleFunctionDao;
import com.frank.entity.Function;
import com.frank.exception.BaseExcetion;
import com.frank.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	FunctionDao functionDao;

	@Autowired
	RoleFunctionDao roleFunctionDao;

	@Override
	public List<Function> queryFunctionByMenuID(Integer menuID) {
		Integer[] menuIDS = new Integer[1];
		menuIDS[0] = menuID;
		return functionDao.queryFunctionByMenuID(menuIDS);
	}

	@Override
	public int addFunction(Function function) {
		return functionDao.addFunction(function);
	}

	@Override
	public int deleteFunction(Integer functionID) {
		return functionDao.deleteFunction(functionID);
	}

	@Override
	@Transactional
	public void deleteFunctionByMenuID(Integer[] menuIDs) {
		try {
			// 查询出所有要删除的方法
			List<Function> functions = functionDao.queryFunctionByMenuID(menuIDs);
			Integer[] functionIDs = new Integer[functions.size()];
			for (int i = 0; i < functions.size(); i++) {
				functionIDs[i] = functions.get(i).getFunctionID();
			}
			// 删除与要删除方法相关的角色方法信息
			roleFunctionDao.deleteRoleFunctionByFunctionID(functionIDs);
			// 删除方法
			functionDao.deleteFunctionByMenuID(menuIDs);
		} catch (Exception e) {
			// 把编译期异常 转化为运行期异常，事务才会回滚
			throw new BaseExcetion("添加用户异常:" + e.getMessage());
		}
	}

}
