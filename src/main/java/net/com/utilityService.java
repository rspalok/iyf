package net.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.model.master.pojo.batch.IYFBatchMst;
import net.model.master.pojo.course.IyfCourseMst;
import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.org.GbltOrgUnitMst;
import net.model.master.pojo.role.GbltRolMst;
import net.model.transection.pojo.courseconfig.IYFCourseConfig;
import net.model.transection.pojo.notice.IyfPublicRegUrlTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

public interface utilityService {

	public List<GbltOrgMst>  getAllOrgDetails();

	public List<GbltRolMst> getAllRoleDetails();

	public List<IYFBatchMst> getBatchList(HttpServletRequest request);

	public List<IyfCourseMst> getCourseList(HttpServletRequest request);

	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request);

	String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);
	
	String studentDetailsByStudentId(String stStudentId, HttpServletRequest objRequest_p);

	public List<GbltOtpStudentRegTrn> studentByStudentId(String stStudentId, HttpServletRequest request);

	public List<GbltOtpStudentRegTrn> getStudentListByMobile(Long mobileNumber, HttpServletRequest objRequest_p);

	public List<GbltOrgUnitMst> allOrgUnits(HttpServletRequest request);

	public List<IyfPublicRegUrlTrn> getUrlList(HttpServletRequest request);
}
