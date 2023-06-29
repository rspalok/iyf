package net.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.model.GbltRolMst;
import net.model.master.pojo.GbltOrgMst;
import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFCourseConfig;
import net.model.master.pojo.IyfCourseMst;

public interface utilityService {

	public List<GbltOrgMst>  getAllOrgDetails();

	public List<GbltRolMst> getAllRoleDetails();

	public List<IYFBatchMst> getBatchList(HttpServletRequest request);

	public List<IyfCourseMst> getCourseList(HttpServletRequest request);

	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request);

}
