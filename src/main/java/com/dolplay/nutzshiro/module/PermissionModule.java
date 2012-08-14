package com.dolplay.nutzshiro.module;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.dolplay.nutzshiro.domain.Permission;
import com.dolplay.nutzshiro.service.PermissionService;

@IocBean
@InjectName
@At("/permission")
public class PermissionModule {

	@Inject
	private PermissionService permissionService;

	@At
	@Ok("jsp:jsp.permission_list")
	@RequiresPermissions("permission:read:*")
	public List<Permission> all() {
		return permissionService.list();
	}

	@At
	@Ok("json")
	@RequiresPermissions("permission:read:*")
	public Map<Long, String> map() {
		return permissionService.map();
	}

	@At
	@Ok(">>:/permission/all")
	@RequiresPermissions("permission:delete:*")
	public void delete(@Param("id") Long id) {
		permissionService.delete(id);
	}

	@At
	@Ok("jsp:jsp.permission_add")
	@RequiresPermissions("permission:create:*")
	public void p_add() {

	}

	@At
	@Ok(">>:/permission/all")
	@RequiresPermissions("permission:create:*")
	public void add(@Param("..") Permission permission) {
		permissionService.insert(permission);
	}

	@At
	@Ok("jsp:jsp.permission_view")
	@RequiresPermissions("permission:read:*")
	public Permission view(@Param("id") Long id) {
		return permissionService.view(id);
	}

	@At
	@Ok(">>:/permission/all")
	@RequiresPermissions("permission:update:*")
	public void edit(@Param("..") Permission permission) {
		permissionService.update(permission);
	}
}
