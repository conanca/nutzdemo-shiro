/* 插入系统初始记录 */
INSERT INTO SYSTEM_USER(ID,NAME,PASSWORD,SALT,SEX,DESCRIPTION) VALUES
(1,'admin',NULL,NULL,NULL,NULL),
(2,'jack',NULL,NULL,'male','test2ete'),
(3,'kate',NULL,NULL,'female','test2ete'),
(4,'sawyer',NULL,NULL,'male','test2ete'),
(5,'john',NULL,NULL,'male','test2ete'),
(6,'ben',NULL,NULL,'male','test2ete');

INSERT INTO SYSTEM_ROLE(ID,NAME,DESCRIPTION) VALUES
(1,'admin','超级管理员：拥有全部权限的角色'),
(2,'viewer','审阅者：拥有任何对象的浏览权限的角色'),
(3,'user-superadmin','用户超级管理员：拥有对用户的任意操作权限的角色'),
(4,'user-admin','用户管理员：拥有对用户的浏览、增加和编辑(不包括删除)权限的角色'),
(5,'security-admin','安全管理员：拥有对角色和权限的任意操作，对用户分配角色及对角色分配权限的权限');

INSERT INTO SYSTEM_PERMISSION(ID,NAME,DESCRIPTION) VALUES
(1,'*:*:*','全部权限'),
(2,'*:read:*','对任何对象的浏览'),
(3,'user:read:*','对用户的浏览'),
(4,'user:read,update:*','对用户的浏览和编辑'),
(5,'user:*:*','对用户的任意操作'),
(6,'user:roleAssign:*','对用户分配角色'),
(7,'role:*:*','对角色的任意操作'),
(8,'permission:*:*','对权限的任意操作'),
(9,'role:PermissionAssign:*','对角色分配权限');

INSERT INTO SYSTEM_USER_ROLE(USERID,ROLEID) VALUES
(1,1),
(2,2),
(2,3),
(3,4),
(4,2),
(5,2),
(6,5);

INSERT INTO SYSTEM_ROLE_PERMISSION(ROLEID,PERMISSIONID) VALUES
(1,1),
(2,2),
(3,5),
(4,4),
(5,6),
(5,7),
(5,8),
(5,9);