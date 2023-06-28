package net.com.service;

import java.util.List;

import net.model.GbltRolMst;
import net.model.master.pojo.GbltOrgMst;

public interface utilityService {

	public List<GbltOrgMst>  getAllOrgDetails();

	public List<GbltRolMst> getAllRoleDetails();

}
