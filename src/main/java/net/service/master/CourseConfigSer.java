package net.service.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFCourseConfig;
import net.model.master.pojo.IyfCourseMst;
import net.model.transection.FollowUpTrn;


public interface CourseConfigSer {
	Page<IYFCourseConfig> findPaginated(int pageNo, int pageSize, String sortField, String sortDir, HttpServletRequest objRequest_p);

	void saveIYFCourseConfig(IYFCourseConfig courseConfig, HttpServletRequest request, HttpServletResponse response);

	IYFCourseConfig getCourseConfigById(Long id,HttpServletRequest request);

	void deleteCourseConfigById(IYFCourseConfig courseConfig,HttpServletRequest request);

	List<IYFBatchMst> getBatchList(HttpServletRequest request);
	List<IyfCourseMst> getCourseList(HttpServletRequest request);

	String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);

	List<IYFCourseConfig> getCourseConfigList( HttpServletRequest objRequest_p);
 
 

}
