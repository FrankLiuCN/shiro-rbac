package com.frank.exception;

public class AddUserExcetion extends BaseExcetion {
	
	public AddUserExcetion(String message) {
		super(message);
	}
	
	public AddUserExcetion(String message,Throwable cause) {
		super(message,cause);
	}
}
