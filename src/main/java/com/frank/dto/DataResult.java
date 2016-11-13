package com.frank.dto;

import java.io.Serializable;

public class DataResult<T> implements Serializable {

	private static final long serialVersionUID = -4890811256662167680L;
	
	private Integer code;
	
	private String msg;
	
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DataResult(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public DataResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public DataResult() {
		super();
	}
	
}
