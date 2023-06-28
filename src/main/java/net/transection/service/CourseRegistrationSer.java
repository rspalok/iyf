package net.transection.service;
 
import java.util.List; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.model.GbltOtpStudentRegTrn;
import net.model.bean.GbltStudentBean;
import net.model.transection.IyfCoureRegTrn;


public interface CourseRegistrationSer {

	void saveIYFCourseConfig(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response);

	public String getCourseRegValidStudent(Long id) ;

	List<IyfCoureRegTrn> getCourseRegValidStudentObj(Long id);

	String getData(Long mICourseConfig, HttpServletRequest objRequest_p);

	void markPresent(String mStudentId, Long mICourseConfig);
 
	public List<GbltOtpStudentRegTrn> getAllRegStudent(Long id) ;

	void deleteIYFCourseConfig(String id, Long configId, HttpServletRequest request, HttpServletResponse response);

	void markattn(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response);

	public List<GbltOtpStudentRegTrn> getAllPresentRegStudent(GbltStudentBean gbltStudentBean);

	public List<GbltOtpStudentRegTrn>  getAllPresentStudent(Long CourseconfigId);
		

}
