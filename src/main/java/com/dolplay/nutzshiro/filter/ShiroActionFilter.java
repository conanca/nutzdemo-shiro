package com.dolplay.nutzshiro.filter;

import java.lang.reflect.Method;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.nutz.lang.Lang;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.HttpStatusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在入口方法中应用Shiro注解来进行安全过滤
 * @author wendal
 *
 */
public class ShiroActionFilter implements ActionFilter {
	private static Logger logger = LoggerFactory.getLogger(ShiroActionFilter.class);

	public View match(final ActionContext actionContext) {
		try {
			ShiroAnnotationsAuthorizingMethodInterceptor.defaultAuth.assertAuthorized(new MethodInvocation() {

				public Object proceed() throws Throwable {
					throw Lang.noImplement();
				}

				public Object getThis() {
					return actionContext.getModule();
				}

				public Method getMethod() {
					return actionContext.getMethod();
				}

				public Object[] getArguments() {
					return actionContext.getMethodArgs();
				}
			});
		} catch (UnauthenticatedException e) {
			logger.warn("用户未登录", e);
			return new HttpStatusView(401);

		} catch (UnauthorizedException e) {
			logger.warn("用户权限不足", e);
			return new HttpStatusView(403);

		} catch (AuthorizationException e) {
			logger.warn("鉴权失败", e);
			return new HttpStatusView(403);
		}
		return null;
	}
}
