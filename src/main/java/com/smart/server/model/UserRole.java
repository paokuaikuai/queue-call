package com.smart.server.model;

import com.smart.mvc.model.PersistentObject;

/**
 * 管理员角色映射
 * 
 * @author WS
 */
public class UserRole extends PersistentObject {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 管理员ID */
	private Integer userId;
	/** 角色ID */
	private Integer roleId;
	

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
