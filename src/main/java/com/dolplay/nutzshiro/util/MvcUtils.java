package com.dolplay.nutzshiro.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;

public class MvcUtils {

	public static Subject getSubject(SecurityManager securityManager, ServletRequest request, ServletResponse response) {
		return new WebSubject.Builder(securityManager, request, response).buildSubject();
	}

}
