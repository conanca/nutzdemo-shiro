package com.dolplay.nutzshiro.filter;

import java.lang.reflect.Method;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.nutz.lang.Lang;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.HttpStatusView;

/**
 * 在入口方法中应用Shiro注解来进行安全过滤
 * @author wendal
 *
 */
public class ShiroActionFilter implements ActionFilter {

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
		} catch (AuthorizationException e) {
			return new HttpStatusView(403);
		}
		return null;
	}
}
