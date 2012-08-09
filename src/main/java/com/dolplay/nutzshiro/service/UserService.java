package com.dolplay.nutzshiro.service;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.IdEntityService;

import com.dolplay.nutzshiro.domain.Role;
import com.dolplay.nutzshiro.domain.User;

@IocBean(fields = { "dao" })
public class UserService extends IdEntityService<User> {

	public UserService() {
		super();
	}

	public UserService(Dao dao) {
		super(dao);
	}

	public List<User> list() {
		return query(null, null);
	}

	public void update(User user) {
		dao().update(user);
	}

	public void insert(User user) {
		dao().insert(user);
	}

	public User view(Long id) {
		return dao().fetchLinks(fetch(id), "roles");
	}

	public User fetchByName(String name) {
		return fetch(Cnd.where("NAME", "=", name));
	}

	public List<String> getRoleNameList(User user) {
		dao().fetchLinks(user, "roles");
		List<String> roleNameList = new ArrayList<String>();
		for (Role role : user.getRoles()) {
			roleNameList.add(role.getName());
		}
		return roleNameList;
	}

	public void addRole(Long userId, Long roleId) {
		User user = fetch(userId);
		Role role = new Role();
		role.setId(roleId);
		user.setRoles(Lang.list(role));
		dao().insertRelation(user, "roles");
	}
	
	public void removeRole(Long userId, Long roleId) {
		dao().clear("SYSTEM_USER_ROLE", Cnd.where("USERID", "=", userId).and("ROLEID", "=", roleId));
	}
}
