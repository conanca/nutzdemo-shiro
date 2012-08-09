package com.dolplay.nutzshiro.domain;

import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

@Table("SYSTEM_USER")
public class User {
	@Id
	private Long id;
	
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String name;
	@Column
	@ColDefine(type = ColType.CHAR, width = 44)
	private String password;
	@Column
	@ColDefine(type = ColType.CHAR, width = 24)
	private String salt;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 10)
	private String sex;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String description;
	@ManyMany(target = Role.class, relation = "SYSTEM_USER_ROLE", from = "USERID", to = "ROLEID")
	private List<Role> roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
