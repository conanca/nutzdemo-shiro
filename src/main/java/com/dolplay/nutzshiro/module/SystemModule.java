package com.dolplay.nutzshiro.module;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dolplay.nutzshiro.util.MvcUtils;

@IocBean
@InjectName
@Filters
public class SystemModule {
	private static Logger logger = LoggerFactory.getLogger(SystemModule.class);

	@Inject
	private SecurityManager securityManager;

	@At("/login")
	public View login(ServletRequest request, ServletResponse response, @Param("username") String username,
			@Param("password") String password, @Param("rememberMe") boolean rememberMe) {
		String host = request.getRemoteHost();
		AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, host);
		try {
			Subject subject = MvcUtils.getSubject(securityManager, request, response);
			ThreadContext.bind(subject);
			subject.login(token);
			return new ViewWrapper(new ServerRedirectView("/"), null);
		} catch (AuthenticationException e) {
			logger.info("验证失败");
			Map<String, Object> msgs = Mvcs.getLocaleMessage("zh_CN");
			String errMsg = msgs.get("login_error").toString();
			return new ViewWrapper(new JspView("/index"), errMsg);
		} catch (Exception e) {
			logger.error("登录失败", e);
			return new ViewWrapper(new JspView("/index"), "登录失败");
		}
	}

	@At("/main")
	@Ok("jsp:jsp.main")
	@RequiresAuthentication
	public void main() {

	}

	@At("/logout")
	@Ok(">>:/")
	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (SessionException ise) {
			logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
		} catch (Exception e) {
			logger.debug("登出发生错误", e);
		}
	}
}
