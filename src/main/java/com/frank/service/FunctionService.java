package com.frank.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.Function;

public interface FunctionService{
	
	List<Function> queryFunctionByMenuID(@Param("menuID")Integer menuID);
	
	int addFunction(Function function);	
	
	int deleteFunction(Integer functionID);
	
	void deleteFunctionByMenuID(Integer[] menuIDs);
}
