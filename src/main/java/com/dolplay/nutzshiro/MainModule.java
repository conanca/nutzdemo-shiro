package com.dolplay.nutzshiro;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.dolplay.nutzshiro.filter.ShiroActionFilter;
import com.dolplay.nutzshiro.module.PermissionModule;
import com.dolplay.nutzshiro.module.RoleModule;
import com.dolplay.nutzshiro.module.SystemModule;
import com.dolplay.nutzshiro.module.UserModule;

@Modules({ UserModule.class, RoleModule.class, SystemModule.class, PermissionModule.class })
@IocBy(type = ComboIocProvider.class, args = { "*org.nutz.ioc.loader.json.JsonLoader", "dao.js", "shiro.js",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.dolplay.nutzshiro" })
@SetupBy(MvcSetup.class)
@Fail("json")
@Filters(@By(type = ShiroActionFilter.class))
@Localization("msg")
public class MainModule {

}
