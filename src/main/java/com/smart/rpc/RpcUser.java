package com.smart.rpc;

import java.io.Serializable;

/**
 * RPC回传用户对象(含菜单)
 * 
 * @author WS
 */
public class RpcUser implements Serializable {

	private static final long serialVersionUID = 4507869346123296527L;

	//用户id
	private Integer userId;
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


	public RpcUser(Integer userId, String account, String realname, String position, Integer departId, Integer roomId) {
		super();
		this.userId = userId;
		this.account = account;
		this.realname = realname;
		this.position = position;
		this.departId = departId;
		this.roomId = roomId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}