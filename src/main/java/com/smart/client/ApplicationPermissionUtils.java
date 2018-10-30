package com.smart.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart.mvc.util.StringUtils;
import com.smart.rpc.AuthenticationRpcService;
import com.smart.rpc.RpcPermission;

/**
 * 当前应用权限初始化
 * 
 * @author WS
 */
public class ApplicationPermissionUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPermissionUtils.class);

	// 应用所有权限URL
	private static Set<String> applicationPermissionSet = null;
	// 应用所有菜单
	private static List<RpcPermission> applicationMenuList = null;
	// 并发监控
	private static Object monitor = new Object();

	/**
	 * 1.应用初始化，获取应用所有的菜单及权限 2.权限有变动修改，JMS通知重新加载
	 */
	public static void initApplicationPermissions(AuthenticationRpcService authenticationRpcService) {
		List<RpcPermission> dbList = null;
		try {
			dbList = authenticationRpcService.findPermissionList(null);
		}
		catch (Exception e) {
			dbList = new ArrayList<RpcPermission>(0);
			LOGGER.error("无法连接到单点登录鉴权系统,请检查配置sso.server.url", e);
		}

		synchronized (monitor) {
			applicationMenuList = new ArrayList<RpcPermission>();
			applicationPermissionSet = new HashSet<String>();
			for (RpcPermission menu : dbList) {
				if (menu.getIsMenu()) {
					applicationMenuList.add(menu);
				}
				if (StringUtils.isNotBlank(menu.getUrl())) {
					applicationPermissionSet.add(menu.getUrl());
				}
			}
		}
	}

	public static Set<String> getApplicationPermissionSet() {
		synchronized (monitor) {
			return applicationPermissionSet;
		}
	}

	public static List<RpcPermission> getApplicationMenuList() {
		synchronized (monitor) {
			return applicationMenuList;
		}
	}
}
