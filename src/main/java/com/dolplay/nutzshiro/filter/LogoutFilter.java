package com.dolplay.nutzshiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.nutz.lang.Strings;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dolplay.nutzshiro.util.MvcUtils;

public class LogoutFilter implements ActionFilter {
	private static Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

	public static final String DEFAULT_LOGOUT_URL = "/logout";
	private String logoutUrl = DEFAULT_LOGOUT_URL;
	private SecurityManager securityManager;

	@Override
	public View match(ActionContext actionContext) {

		String path = actionContext.getPath();
		if (!Strings.isEmpty(path) && path.equals(logoutUrl)) {

			Subject currentUser = MvcUtils.getSubject(securityManager, actionContext.getRequest(),
					actionContext.getResponse());
			try {
				currentUser.logout();
			} catch (SessionException ise) {
				logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
			}
		}

		return null;
	}
}
