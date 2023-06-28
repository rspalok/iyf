package net.master.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.GbltOtpStudentRegTrn;
import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFCourseConfig;
import net.model.master.pojo.IyfCourseMst;


public interface CourseConfigSer {
	Page<IYFCourseConfig> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

	void saveIYFCourseConfig(IYFCourseConfig courseConfig, HttpServletRequest request, HttpServletResponse response);

	IYFCourseConfig getCourseConfigById(Long id);

	void deleteCourseConfigById(IYFCourseConfig courseConfig);

	List<IYFBatchMst> getBatchList();
	List<IyfCourseMst> getCourseList();

	String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);

	List<IYFCourseConfig> getCourseConfigList();
 
 

}
