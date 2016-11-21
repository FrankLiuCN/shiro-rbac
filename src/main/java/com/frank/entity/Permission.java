package com.frank.entity;

public class Permission {
	
	private boolean isAllow;
		
	private Function function;

	public boolean isAllow() {
		return isAllow;
	}

	public void setAllow(boolean isAllow) {
		this.isAllow = isAllow;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
}
