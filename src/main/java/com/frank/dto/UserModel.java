package com.frank.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.frank.entity.Role;
import com.frank.entity.User;
import com.frank.enums.Status;

public class UserModel implements Serializable  {
	
	private static final long serialVersionUID = 5558917688393269186L;

	private Integer userID;
	
	private String loginName;
	
	private String nickName;
	
	private String password;

	private Timestamp createTime;
	
	private Timestamp lastLoginTime;
	
	private Integer status;
	
	private String  statusName;	
	
	private List<Role> roles;
	
	public UserModel(){
	}
	
	public UserModel(User user,List<Role> roles){
		userID=user.getUserID();
		loginName=user.getLoginName();
		nickName=user.getNickName();
		password=user.getPassword();
		createTime=user.getCreateTime();
		lastLoginTime=user.getLastLoginTime();
		status=user.getStatus();
		statusName=Status.stateOf(user.getStatus()).getName();
		this.roles=roles;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
