package net.report.service;

import javax.servlet.http.HttpServletRequest;

public interface DashboardSer {

	public String getCourseAllCourserRegByStudentId(String mStudentId);

	public String getCourseAllCourserRegByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);

}
