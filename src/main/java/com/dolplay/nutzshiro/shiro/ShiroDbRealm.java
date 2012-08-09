package com.dolplay.nutzshiro.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.dolplay.nutzshiro.domain.Role;
import com.dolplay.nutzshiro.domain.User;
import com.dolplay.nutzshiro.service.RoleService;
import com.dolplay.nutzshiro.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	private UserService userService;
	private RoleService roleService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.fetchByName(token.getUsername());
		if (user != null) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
			ByteSource salt = ByteSource.Util.bytes(user.getSalt());
			info.setCredentialsSalt(salt);
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = (String) principals.fromRealm(getName()).iterator().next();
		User user = userService.fetchByName(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addRoles(userService.getRoleNameList(user));
			for (Role role : user.getRoles()) {
				info.addStringPermissions(roleService.getPermissionNameList(role));
			}
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}