package com.dolplay.nutzshiro.filter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.util.WebUtils;
import org.nutz.lang.Strings;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dolplay.nutzshiro.util.MvcUtils;

public class AuthenticationFilter implements ActionFilter {
	final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	public static final String DEFAULT_USERNAME_PARAM = "username";
	public static final String DEFAULT_PASSWORD_PARAM = "password";
	public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
	public static final String DEFAULT_LOGIN_URL = "/login";

	private String loginUrl = DEFAULT_LOGIN_URL;
	private String usernameParam = DEFAULT_USERNAME_PARAM;
	private String passwordParam = DEFAULT_PASSWORD_PARAM;
	private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;
	private SecurityManager securityManager;

	@Override
	public View match(ActionContext actionContext) {
		View view = null;
		String path = actionContext.getPath();
		if (!Strings.isEmpty(path) && path.equals(loginUrl)) {
			try {
				boolean isLogin = executeLogin(actionContext.getRequest(), actionContext.getResponse());
				if (!isLogin) {
					Map<String, Object> msgs = Mvcs.getLocaleMessage("zh_CN");
					String errMsg = msgs.get("login_error").toString();
					view = new ViewWrapper(new JspView("/index"), errMsg);
					logger.info("登录失败");
				}
			} catch (Exception e) {
				view = new ViewWrapper(new JspView("/index"), "登录出错");
			}
		}

		return view;
	}

	protected String getHost(ServletRequest request) {
		return request.getRemoteHost();
	}

	protected AuthenticationToken createToken(String username, String password, boolean rememberMe, String host) {
		return new UsernamePasswordToken(username, password, rememberMe, host);
	}

	protected AuthenticationToken createToken(String username, String password, boolean rememberMe,
			ServletRequest request, ServletResponse response) {
		String host = getHost(request);
		return createToken(username, password, rememberMe, host);
	}

	protected String getUsername(ServletRequest request) {
		return WebUtils.getCleanParam(request, getUsernameParam());
	}

	protected String getPassword(ServletRequest request) {
		return WebUtils.getCleanParam(request, getPasswordParam());
	}

	protected boolean isRememberMe(ServletRequest request) {
		return WebUtils.isTrue(request, getRememberMeParam());
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		// TODO password可加密
		boolean rememberMe = isRememberMe(request);
		return createToken(username, password, rememberMe, request, response);
	}

	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		return true;
	}

	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		return false;
	}

	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
					+ "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}
		try {
			Subject subject = MvcUtils.getSubject(securityManager, request, response);
			ThreadContext.bind(subject);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	public String getUsernameParam() {
		return usernameParam;
	}

	public void setUsernameParam(String usernameParam) {
		this.usernameParam = usernameParam;
	}

	public String getPasswordParam() {
		return passwordParam;
	}

	public void setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
	}

	public String getRememberMeParam() {
		return rememberMeParam;
	}

	public void setRememberMeParam(String rememberMeParam) {
		this.rememberMeParam = rememberMeParam;
	}

	public String getLoginUrl() {
		return loginUrl;
	}
}
