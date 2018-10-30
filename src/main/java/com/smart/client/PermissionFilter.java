package com.smart.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.smart.mvc.exception.ServiceException;
import com.smart.rpc.RpcPermission;

/**
 * 权限控制Filter
 * 
 * @author WS
 */
public class PermissionFilter extends ClientFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		ApplicationPermissionUtils.initApplicationPermissions(authenticationRpcService);
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = request.getServletPath();
		if (isPermitted(request, path))
			chain.doFilter(request, response);
		else if (!ApplicationPermissionUtils.getApplicationPermissionSet().contains(path))
			chain.doFilter(request, response);
		else {
			throw new ServiceException(SsoResultCode.SSO_PERMISSION_ERROR, "没有访问权限");
		}
	}

	private boolean isPermitted(HttpServletRequest request, String path) {
		Set<String> permissionSet = getLocalPermissionSet(request);
		return permissionSet.contains(path);
	}

	private Set<String> getLocalPermissionSet(HttpServletRequest request) {
		SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
		if (sessionPermission == null || sessionPermissionChanged(request)) {
			sessionPermission = invokePermissionInSession(request);
		}
		return sessionPermission.getPermissionSet();
	}

	private boolean sessionPermissionChanged(HttpServletRequest request) {
		SessionUser user = SessionUtils.getSessionUser(request);
		return PermissionJmsMonitor.isChanged && !PermissionJmsMonitor.tokenSet.contains(user.getToken());
	}

	/**
	 * 保存权限信息
	 * 
	 * @param token
	 * @return
	 */
	public SessionPermission invokePermissionInSession(HttpServletRequest request) {
		SessionUser user = SessionUtils.getSessionUser(request);
		List<RpcPermission> dbList = authenticationRpcService.findPermissionList(user.getToken());

		List<RpcPermission> menuList = new ArrayList<RpcPermission>();
		Set<String> operateSet = new HashSet<String>();
		for (RpcPermission menu : dbList) {
			if (menu.getIsMenu()) {
				menuList.add(menu);
			}
			if (menu.getUrl() != null) {
				operateSet.add(menu.getUrl());
			}
		}

		SessionPermission sessionPermission = new SessionPermission();
		// 设置登录用户菜单列表
		sessionPermission.setMenuList(menuList);

		// 保存登录用户没有权限的URL，方便前端去隐藏相应操作按钮
		Set<String> noPermissionSet = new HashSet<String>(ApplicationPermissionUtils.getApplicationPermissionSet());
		noPermissionSet.removeAll(operateSet);

		sessionPermission.setNoPermissions(StringUtils.arrayToDelimitedString(noPermissionSet.toArray(), ","));

		// 保存登录用户权限列表
		sessionPermission.setPermissionSet(operateSet);
		SessionUtils.setSessionPermission(request, sessionPermission);

		// 添加权限监控集合，当前session已更新最新权限
		if (PermissionJmsMonitor.isChanged) {
			PermissionJmsMonitor.tokenSet.add(user.getToken());
		}
		return sessionPermission;
	}
}