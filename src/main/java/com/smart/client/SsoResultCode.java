package com.smart.client;

import com.smart.mvc.model.ResultCode;

/**
 * 单点登录权限返回码
 * 
 * @author WS
 */
public class SsoResultCode extends ResultCode {
	
	// SSO 用户授权出错
	public final static int SSO_TOKEN_ERROR = 1001; // TOKEN未授权或已过期
	public final static int SSO_PERMISSION_ERROR = 1002; // 没有访问权限
}
