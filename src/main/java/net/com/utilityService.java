package net.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.model.master.GbltOrgMst;
import net.model.master.GbltRolMst;
import net.model.master.IYFBatchMst;
import net.model.master.IyfCourseMst;
import net.model.transection.IYFCourseConfig;

public interface utilityService {

	public List<GbltOrgMst>  getAllOrgDetails();

	public List<GbltRolMst> getAllRoleDetails();

	public List<IYFBatchMst> getBatchList(HttpServletRequest request);

	public List<IyfCourseMst> getCourseList(HttpServletRequest request);

	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request);

}
