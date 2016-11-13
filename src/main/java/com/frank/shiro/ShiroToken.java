package com.frank.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken  implements java.io.Serializable{
	
	private static final long serialVersionUID = -6451794657814516274L;

	public ShiroToken(String username, String pswd) {
		super(username,pswd);
		this.pswd = pswd ;
	}
		
	/** ��¼����[�ַ�������] ��Ϊ������char[] ] **/
	private String pswd ;

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
}