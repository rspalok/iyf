package net.transection.login.dao;

import net.model.master.pojo.role.GbltRolMst;

public interface RoleDao {

	public GbltRolMst findRoleByName(Integer integer);
	
}
