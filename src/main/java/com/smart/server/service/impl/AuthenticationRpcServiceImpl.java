package com.smart.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smart.mvc.util.StringUtils;
import com.smart.rpc.AuthenticationRpcService;
import com.smart.rpc.RpcPermission;
import com.smart.rpc.RpcUser;
import com.smart.server.common.LoginUser;
import com.smart.server.common.TokenManager;
import com.smart.server.service.PermissionService;
import com.smart.server.service.UserService;

@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private PermissionService permissionService;
	@Resource
	private UserService userService;
	@Resource
	private TokenManager tokenManager;

	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}

	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getUserId(), user.getAccount(),user.getRealname(),user.getPosition(),user.getDepartId(),user.getRoomId());
		}
		return null;
	}

	public List<RpcPermission> findPermissionList(String token) {
		if (StringUtils.isBlank(token)) {
			return permissionService.findListById( null);
		} else {
			LoginUser user = tokenManager.validate(token);
			if (user != null) {
				return permissionService.findListById(user.getUserId());
			} else {
				return new ArrayList<RpcPermission>(0);
			}
		}
	}
}
