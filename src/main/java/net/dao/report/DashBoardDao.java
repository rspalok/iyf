package net.dao.report;

import java.util.Date;
import java.util.List;

import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCourseAttenTrn;

public interface DashBoardDao {

	public List<IyfCourseAttenTrn> getCourseAllCourserRegByStudentIdfromAtten(String orgId,String stStudentId);

	public List<IyfCourseAttenTrn> getCourseAllCourserRegByMobileNo(String orgId, Long mobileNumber);

	public List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(Date date1, Long getmICourseConfig,
			String org);


}
