package net.report.dasboard.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.attendance.IyfCourseAttenTrn;

public interface DashBoardDao {

	public List<IyfCourseAttenTrn> getCourseAllCourserRegByStudentIdfromAtten(String orgId,String stStudentId);

	public List<IyfCourseAttenTrn> getCourseAllCourserRegByMobileNo(String orgId, Long mobileNumber);

	public List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(Date date1, Long getmICourseConfig,
			String org);

	public  List<Map> ClassAttendance(String studentId, Set<Long> courseSet, String org);

	public List<Map> getAttendanceCount(Long mICourseConfig, String org);


}
