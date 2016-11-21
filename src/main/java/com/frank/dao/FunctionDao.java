package com.frank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.entity.Function;

public interface FunctionDao {
	
	List<Function> queryFunctionByMenuID(Integer[] menuIDs);
		
	int addFunction(Function function);	
	
	int deleteFunction(Integer functionID);
	
	int deleteFunctionByMenuID(Integer[] menuIDs);
}
