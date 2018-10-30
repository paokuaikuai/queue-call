package com.smart.client;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smart.mvc.exception.ServiceException;
import com.smart.rpc.RpcUser;

/**
 * 单点登录及Token验证Filter
 * 
 * @author WS
 */
public class SsoFilter extends ClientFilter {

	// sso授权回调参数token名称
	public static final String SSO_TOKEN_NAME = "__vt_param__";

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = getLocalToken(request);

		if (token == null) {
			if (getParameterToken(request) != null) {
				// 再跳转一次当前URL，以便去掉URL中token参数
				response.sendRedirect(request.getRequestURL().toString());
			}
			else
				redirectLogin(request, response);
		}
		else if (isLogined(token))
			chain.doFilter(request, response);
		else
			redirectLogin(request, response);
	}

	/**
	 * 获取Session中token
	 * 
	 * @param request
	 * @return
	 */
	private String getLocalToken(HttpServletRequest request) {
		SessionUser sessionUser = SessionUtils.getSessionUser(request);
		return sessionUser == null ? null : sessionUser.getToken();
	}

	/**
	 * 获取服务端回传token参数且验证
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String getParameterToken(HttpServletRequest request) throws IOException {
		String token = request.getParameter(SSO_TOKEN_NAME);
		if (token != null) {
			RpcUser rpcUser = authenticationRpcService.findAuthInfo(token);
			if (rpcUser != null) {
				invokeAuthenticationInfoInSession(request,rpcUser.getUserId(), token, rpcUser.getAccount(),rpcUser.getRealname(),rpcUser.getPosition(),rpcUser.getDepartId(),rpcUser.getRoomId());
				return token;
			}
		}
		return null;
	}

	/**
	 * 跳转登录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (isAjaxRequest(request)) {
			throw new ServiceException(SsoResultCode.SSO_TOKEN_ERROR, "未登录或已超时");
		}
		else {
			SessionUtils.invalidate(request);
			String ssoLoginUrl = new StringBuilder().append(ssoServerUrl).append("/login?backUrl=")
					.append(request.getRequestURL()).toString();

			response.sendRedirect(ssoLoginUrl);
		}
	}

	/**
	 * 保存认证信息到Session
	 * 
	 * @param token
	 * @param account
	 * @param profile
	 */
	private void invokeAuthenticationInfoInSession(HttpServletRequest request, Integer userId, String token, String account,String realname,String position,Integer departId,Integer roomId) {
		SessionUtils.setSessionUser(request, new SessionUser(userId, token, account,realname,position,departId,roomId));
	}

	/**
	 * 是否已登录
	 * 
	 * @param token
	 * @return
	 */
	private boolean isLogined(String token) {
		return authenticationRpcService.validate(token);
	}

	/**
	 * 是否Ajax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
}