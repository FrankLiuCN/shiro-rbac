package com.frank.exception;

//ϵͳ����쳣
public class BaseExcetion extends RuntimeException{

	public BaseExcetion(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseExcetion(String message) {
		super(message);
	}

}
