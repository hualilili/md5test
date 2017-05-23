package com.primeton.authentication.handler;

import com.primeton.authentication.IUserCache;
import com.primeton.authentication.UserCacheConfig;
import com.primeton.authentication.UserCacheUtil;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.util.StringUtils;

public class CacheUsernamePasswordAuthenticationHandler {
	private IUserCache userCache = null;

	private UserCacheConfig userCacheConfig = null;

	protected boolean authenticateUsernamePasswordInternal(
			UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		if ((StringUtils.hasText(username)) && (StringUtils.hasText(password))) {
			String encodePassword = this.userCache.getPassword(username);
			if (encodePassword != null) {
				if (getPasswordEncoder().encode(password)
						.equals(encodePassword))
					return true;
			}
		}
		return false;
	}

	public void init() {
		UserCacheUtil uct = new UserCacheUtil();
		uct.setUserCacheConfig(getUserCacheConfig());
		uct.setUserCache(getUserCache());
		uct.loadUserInfo();
		uct.startUpdateTask();
	}

	public IUserCache getUserCache() {
		return this.userCache;
	}

	public void setUserCache(IUserCache userCache) {
		this.userCache = userCache;
	}

	public UserCacheConfig getUserCacheConfig() {
		return this.userCacheConfig;
	}

	public void setUserCacheConfig(UserCacheConfig userCacheConfig) {
		this.userCacheConfig = userCacheConfig;
	}
}
