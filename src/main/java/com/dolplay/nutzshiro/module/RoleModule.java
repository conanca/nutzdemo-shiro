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

import com.dolplay.nutzshiro.domain.Role;
import com.dolplay.nutzshiro.service.RoleService;

@IocBean
@InjectName
@At("/role")
public class RoleModule {
	@Inject
	private RoleService roleService;

	@At
	@Ok("jsp:jsp.role_list")
	@RequiresPermissions("role:read:*")
	public List<Role> all() {
		return roleService.list();
	}

	@At
	@Ok("json")
	@RequiresPermissions("role:read:*")
	public Map<Long, String> map() {
		return roleService.map();
	}

	@At
	@Ok("jsp:jsp.role_add")
	@RequiresPermissions("role:create:*")
	public void p_add() {

	}

	@At
	@Ok(">>:/role/all")
	@RequiresPermissions("role:create:*")
	public void add(@Param("..") Role role) {
		roleService.insert(role);
	}

	@At
	@Ok(">>:/role/all")
	@RequiresPermissions("role:delete:*")
	public void delete(@Param("id") Long id) {
		roleService.delete(id);
	}

	@At
	@Ok("jsp:jsp.role_view")
	@RequiresPermissions("role:read:*")
	public Role view(@Param("id") Long id) {
		return roleService.view(id);
	}

	@At
	@Ok(">>:/role/all")
	@RequiresPermissions("role:update:*")
	public void edit(@Param("..") Role role) {
		roleService.update(role);
	}

	@At
	@Ok(">>:/role/view?id=${p.roleId}")
	@RequiresPermissions("role:permissionAssign:*")
	public void addPermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId) {
		roleService.addPermission(roleId, permissionId);
	}

	@At
	@Ok(">>:/role/view?id=${p.roleId}")
	@RequiresPermissions("role:permissionAssign:*")
	public void removePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId) {
		roleService.removePermission(roleId, permissionId);
	}
}
