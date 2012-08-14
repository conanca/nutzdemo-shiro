package com.dolplay.nutzshiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.dolplay.nutzshiro.domain.Permission;
import com.dolplay.nutzshiro.domain.Role;
import com.dolplay.nutzshiro.domain.User;

public class MvcSetup implements Setup {

	@Override
	public void init(NutConfig config) {
		Ioc ioc = config.getIoc();
		Dao dao = ioc.get(Dao.class);

		//若必要的数据表不存在，则初始化数据库
		if (!dao.exists(User.class)) {
			dao.create(User.class, true);
			dao.create(Role.class, true);
			dao.create(Permission.class, true);
			FileSqlManager fm = new FileSqlManager("init_system_h2.sql");
			List<Sql> sqlList = fm.createCombo(fm.keys());
			dao.execute(sqlList.toArray(new Sql[sqlList.size()]));
			// 初始化用户密码（全部都是123）及salt
			List<User> userList = dao.query(User.class, null);
			for (User user : userList) {
				RandomNumberGenerator rng = new SecureRandomNumberGenerator();
				String salt = rng.nextBytes().toBase64();
				String hashedPasswordBase64 = new Sha256Hash("123", salt, 1024).toBase64();
				user.setSalt(salt);
				user.setPassword(hashedPasswordBase64);
				dao.update(user);
			}
		}

		// 设置 Shiro 的 securityManager
		SecurityManager securityManager = ioc.get(SecurityManager.class);
		SecurityUtils.setSecurityManager(securityManager);
	}

	@Override
	public void destroy(NutConfig config) {
		// TODO Auto-generated method stub

	}

}
