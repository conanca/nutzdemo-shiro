var ioc = {
		
	systemModule : {
		type : "com.dolplay.nutzshiro.module.SystemModule"
	},

	userService : {
		type : "com.dolplay.nutzshiro.service.UserService",
		args : [ {
			refer : "dao"
		} ]
	},

	userModule : {
		type : "com.dolplay.nutzshiro.module.UserModule",
		fields : {
			userService : {
				refer : "userService"
			}
		}
	},

	roleService : {
		type : "com.dolplay.nutzshiro.service.RoleService",
		args : [ {
			refer : "dao"
		} ]
	},

	roleModule : {
		type : "com.dolplay.nutzshiro.module.RoleModule",
		fields : {
			roleService : {
				refer : "roleService"
			}
		}
	},

	permissionService : {
		type : "com.dolplay.nutzshiro.service.PermissionService",
		args : [ {
			refer : "dao"
		} ]
	},

	permissionModule : {
		type : "com.dolplay.nutzshiro.module.PermissionModule",
		fields : {
			permissionService : {
				refer : "permissionService"
			}
		}
	}
};