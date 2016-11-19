package com.frank.exception;

//系统相关异常
public class BaseExcetion extends RuntimeException{

	public BaseExcetion(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseExcetion(String message) {
		super(message);
	}

}
