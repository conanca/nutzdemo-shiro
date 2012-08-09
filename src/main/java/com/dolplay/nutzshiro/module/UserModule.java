package com.dolplay.nutzshiro.module;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.dolplay.nutzshiro.annotation.Authorization;
import com.dolplay.nutzshiro.domain.User;
import com.dolplay.nutzshiro.service.UserService;

@IocBean
@InjectName
@At("/user")
public class UserModule {

	@Inject
	private UserService userService;

	@At
	@Ok("jsp:jsp.user_list")
	@Authorization(requiresPermissions = "user:read:*")
	public List<User> all() {
		return userService.list();
	}

	@At
	@Ok(">>:/user/all")
	@Authorization(requiresPermissions = "user:delete:*")
	public void delete(@Param("id") Long id) {
		userService.delete(id);
	}

	@At
	@Ok(">>:/user/all")
	@Authorization(requiresPermissions = "user:update:*")
	public void edit(@Param("..") User user) {
		userService.update(user);
	}

	@At
	@Ok(">>:/user/all")
	@Authorization(requiresPermissions = "user:create:*")
	public void add(@Param("..") User user) {
		userService.insert(user);
	}

	@At
	@Ok("jsp:jsp.user_view")
	@Authorization(requiresPermissions = "user:read:*")
	public User view(@Param("id") Long id) {
		return userService.view(id);
	}

	@At
	@Ok("jsp:jsp.user_add")
	@Authorization(requiresRolesAtLeastOne = "admin,user-superadmin,user-admin")
	public void p_add() {

	}
	
	@At
	@Ok(">>:/user/view?id=${p.userId}")
	@Authorization(requiresPermissions = "user:roleAssign:*")
	public void addRole(@Param("userId") Long userId,@Param("roleId") Long roleId) {
		userService.addRole(userId, roleId);
	}

	@At
	@Ok(">>:/user/view?id=${p.userId}")
	@Authorization(requiresPermissions = "user:roleAssign:*")
	public void removeRole(@Param("userId") Long userId,@Param("roleId") Long roleId) {
		userService.removeRole(userId, roleId);
	}
}
