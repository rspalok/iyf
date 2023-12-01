package net.transection.attendance.service;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.model.bean.GbltStudentBean;
import net.model.transection.pojo.attendance.IyfCourseAttenTrn;


public interface CourseRegistrationSer {

	public void registerStudentForTheCourse(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response);

 
	public String getAllRegStudent(Long id, HttpServletRequest objRequest_p) ;

	
	public String markattn(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response);

	public List<IyfCourseAttenTrn>  getAllPresentStudent(GbltStudentBean CourseconfigId, HttpServletRequest objRequest_p);

	public void removeRegistration(GbltStudentBean gbltStudentBean, HttpServletRequest request,
			HttpServletResponse response);


	public String getAllPresentStudentInClass(GbltStudentBean gbltStudentBean,
			HttpServletRequest objRequest_p);


	public Integer attenInfo(Long mICourseConfig, Long classId, HttpServletRequest request);


	public Integer regInfo(Long mICourseConfig,HttpServletRequest request);
		

}
