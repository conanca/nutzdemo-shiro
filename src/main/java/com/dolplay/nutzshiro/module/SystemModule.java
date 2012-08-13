package com.dolplay.nutzshiro.module;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.dolplay.nutzshiro.filter.AuthenticationFilter;

@IocBean
@InjectName
public class SystemModule {

	@At("/login")
	@Ok(">>:/")
	@Filters({ @By(type = AuthenticationFilter.class, args = { "ioc:authenticationFilter" }) })
	public void login() {

	}
	
	@At("/logout")
	@Ok(">>:/")
	@Filters({ @By(type = AuthenticationFilter.class, args = { "ioc:logoutFilter" }) })
	public void logout() {

	}
}
