package com.smart.server.common;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;

import com.smart.mvc.cache.RedisCache;

/**
 * 分布式环境令牌管理
 * 
 * @author WS
 */
public class RedisTokenManager extends TokenManager {

	/**
	 * 是否需要扩展token过期时间
	 */
	private Set<String> tokenSet = new CopyOnWriteArraySet<String>();

	@Resource
	private RedisCache<LoginUser> redisCache;

	@Override
	public void addToken(String token, LoginUser loginUser) {
		redisCache.set(token, loginUser, tokenTimeout * 1000);
	}

	@Override
	public LoginUser validate(String token) {
		LoginUser loginUser = redisCache.get(token);
		if (loginUser != null && !tokenSet.contains(token)) {
			tokenSet.add(token);
			addToken(token, loginUser);
		}
		return loginUser;
	}

	@Override
	public void remove(String token) {
		redisCache.delete(token);
	}

	@Override
	public void verifyExpired() {
		tokenSet.clear();
	}
}
