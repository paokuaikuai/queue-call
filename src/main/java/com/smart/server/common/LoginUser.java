package com.smart.server.common;

import java.io.Serializable;

/**
 * 登录成功用户对象
 * 
 * @author WS
 */
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 4507869346123296527L;

	// 登录成功ID
	private Integer userId;
	// 登录成功用户名
	private String account;
	//真名
	private String realname;
	//职位
	private String position;
	//科室ID
	private Integer departId;
	//诊室ID
	private Integer roomId;
	

	public LoginUser(Integer userId, String account, String realname, String position, Integer departId,
			Integer roomId) {
		super();
		this.userId = userId;
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



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginUser other = (LoginUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		}
		else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}