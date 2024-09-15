package net.report.dasboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.model.bean.GbltStudentBean;
import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.attendance.IyfCourseAttenTrn;
import net.model.transection.pojo.schedule.IyfClassSchedTrn;

public interface DashboardSer {

	public String getAllCourserRegStudentByStudentId(String mStudentId, HttpServletRequest objRequest_p);

	public String getAllCourserRegStudentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);

	public List<IyfClassSchedTrn> getScheduleClassAgainstCourseConfig(Long getmICourseConfig, HttpServletRequest request);

	public List<IyfCourseAttenTrn> getAllPresentStudentList(Long getmICourseConfig, Long getmClassId, HttpServletRequest request);

	public List<IyfCoureRegTrn>  getRagisterdStudentOnDateandCourseConfig(GbltStudentBean gbltStudentBean, HttpServletRequest request);

	public List<IyfCoureRegTrn> getAllRagisterdCourseList(GbltStudentBean gbltStudentBean, HttpServletRequest request);

	public HashMap<Long, List<IyfClassSchedTrn>> getClassListHasMap(String StudentId, Set<Long> setCourse, HttpServletRequest request);

	List<Map> getAttenListHasMap(String studentId, Set<Long> courseSet, HttpServletRequest request);

	List<Map> getAttendanceCount(Long getmICourseConfig, HttpServletRequest request);

	public List<Map> studentOtpReport(GbltStudentBean gbltStudentBean, HttpServletRequest request);

	public List<IyfCoureRegTrn> getAllRegisterdStudentList(GbltStudentBean gbltStudentBean, HttpServletRequest request);

	public void genReport(GbltStudentBean gbltStudentBean, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) throws Exception;

}
