package com.smart.client;

import java.io.Serializable;

/**
 * 已登录用户信息
 * 
 * @author WS
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1764365572138947234L;

	//用户Id
	private Integer userId;
	// 登录用户访问Token
	private String token;
	// 登录名
	private String account;
	//真名
	private String realname;
	//职位
	private String position;
	//科室ID
	private Integer departId;
	//诊室ID
	private Integer roomId;

	public SessionUser() {
		super();
	}
    

	public SessionUser(Integer userId, String token, String account, String realname, String position, Integer departId,
			Integer roomId) {
		super();
		this.userId = userId;
		this.token = token;
		this.account = account;
		this.realname = realname;
		this.position = position;
		this.departId = departId;
		this.roomId = roomId;
	}

	public Integer getRoomId() {
		return roomId;
	}


	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public String getRealname() {
		return realname;
	}



	public void setRealname(String realname) {
		this.realname = realname;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public Integer getDepartId() {
		return departId;
	}



	public void setDepartId(Integer departId) {
		this.departId = departId;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
