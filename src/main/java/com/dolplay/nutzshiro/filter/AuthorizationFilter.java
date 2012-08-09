package com.dolplay.nutzshiro.filter;

import java.util.List;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.HttpStatusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dolplay.nutzshiro.annotation.Authorization;
import com.dolplay.nutzshiro.util.MvcUtils;

public class AuthorizationFilter implements ActionFilter {
	final static Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	private SecurityManager securityManager;

	private static final View UNAUTH = new HttpStatusView(403);

	@Override
	public View match(ActionContext actionContext) {
		Authorization a = actionContext.getMethod().getAnnotation(Authorization.class);
		if (a == null) {
			return null;
		}

		Subject currentUser = MvcUtils.getSubject(securityManager, actionContext.getRequest(),
				actionContext.getResponse());

		if (a.requiresUser()) {
			if (!currentUser.isAuthenticated()) {
				logger.info("用户未登录,无法执行操作!");
				return UNAUTH;
			}
		}
		if (a.requiresGuest()) {
			PrincipalCollection principals = currentUser.getPrincipals();
			if (principals != null && !principals.isEmpty()) {
				logger.info("已登录用户,无法执行操作!");
				return UNAUTH;
			}
		}
		String requiresRolesAllStr = a.requiresRolesAll();
		if (!Strings.isEmpty(requiresRolesAllStr)) {
			List<String> requiresAllRoles = Lang.array2list(requiresRolesAllStr.split(","));
			if (!currentUser.hasAllRoles(requiresAllRoles)) {
				logger.info("用户未拥有该操作所需的所有角色,无法执行操作!");
				return UNAUTH;
			}
		}
		String requiresAtLeastOneRolesStr = a.requiresRolesAtLeastOne();
		if (!Strings.isEmpty(requiresAtLeastOneRolesStr)) {
			List<String> requiresAtLeastOneRoles = Lang.array2list(requiresAtLeastOneRolesStr.split(","));
			if (!isTrue(currentUser.hasRoles(requiresAtLeastOneRoles), false)) {
				logger.info("用户未拥有该操作所需的任一角色,无法执行操作!");
				return UNAUTH;
			}
		}
		String requiresPermissionsStr = a.requiresPermissions();
		if(!Strings.isEmpty(requiresPermissionsStr)){
			String[] requiresPermissions = requiresPermissionsStr.split(",");
			if (!(currentUser.isPermittedAll(requiresPermissions))) {
				logger.info("用户未拥有该操作所需的所有权限,无法执行操作!");
				return UNAUTH;
			}
		}
		return null;
	}

	private static boolean isTrue(boolean[] boolArr, boolean isAnd) {
		boolean isTrue;
		int length = boolArr.length;
		int i;
		if (isAnd) {
			isTrue = true;
			i = 0;
			while (i < length) {
				if (boolArr[i] == false) {
					return false;
				}
				i++;
			}
		} else {
			isTrue = false;
			i = 0;
			while (i < length) {
				if (boolArr[i] == true) {
					return true;
				}
				i++;
			}
		}
		return isTrue;
	}
}
