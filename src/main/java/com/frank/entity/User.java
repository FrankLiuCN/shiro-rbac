package com.frank.entity;

import java.sql.Timestamp;

public class User {
	
	private String userID;
	
	private String loginName;
	
	private String nickName;
	
	private String password; 
	
	private Timestamp createTime;
	
	private Timestamp lastLoginTime;
	
	private Integer status;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", loginName=" + loginName + ", nickName=" + nickName + ", password="
				+ password + ", createTime=" + createTime + ", lastLoginTime=" + lastLoginTime + ", status=" + status
				+ "]";
	}
	
}
