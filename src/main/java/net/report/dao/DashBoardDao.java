package net.report.dao;

import java.util.List;

import net.model.transection.IyfCourseAttenTrn;

public interface DashBoardDao {

	public List<IyfCourseAttenTrn> getCourseAllCourserRegByStudentIdfromAtten(String orgId,String stStudentId);

	public List<IyfCourseAttenTrn> getCourseAllCourserRegByMobileNo(String orgId, Long mobileNumber);

}
