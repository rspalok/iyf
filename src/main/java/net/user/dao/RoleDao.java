package net.user.dao;

import net.model.GbltRolMst;

public interface RoleDao {

	public GbltRolMst findRoleByName(String theRoleName);
	
}
