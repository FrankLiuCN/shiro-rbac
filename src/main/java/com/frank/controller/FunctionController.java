package com.frank.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.dto.DataResult;
import com.frank.entity.Function;
import com.frank.service.FunctionService;

@Controller
public class FunctionController {

	@Autowired
	FunctionService functionService;
	
	@RequiresPermissions("function:view")
	@RequestMapping(value = "/function/{menuID}/list")
	@ResponseBody
	public DataResult<List<Function>> queryFunctionByMenuID(@PathVariable("menuID")Integer menuID) {
		List<Function> functions=functionService.queryFunctionByMenuID(menuID);
		DataResult<List<Function>> result=new DataResult<List<Function>>(0,"执行成功",functions);
		return result;
	}
	
	@RequiresPermissions("function:add")
	@RequestMapping(value = "/function/add", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> addFunction(Function function) {	
		functionService.addFunction(function);
		DataResult<String> result=new DataResult<String>(0,"执行成功");		
		return result;
	}
	
	@RequiresPermissions("function:delete")
	@RequestMapping(value = "/function/delete", method = RequestMethod.POST)
	@ResponseBody
	public DataResult<String> deleteFunction(Integer functionID) {	
		functionService.deleteFunction(functionID);
		DataResult<String> result=new DataResult<String>(0,"执行成功");		
		return result;
	}
}
