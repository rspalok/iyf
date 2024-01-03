package net.master.journey.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.master.pojo.course.IyfCourseMst;
import net.model.master.pojo.org.GbltOrgMst;

public interface CourseSer {

	Page<IyfCourseMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest request);

	void saveIyfCourseMst(IyfCourseMst batch, HttpServletRequest request, HttpServletResponse response);

	IyfCourseMst getOrgUnitById(Integer id,HttpServletRequest request);

	void deleteOrgUnitById(IyfCourseMst batch,HttpServletRequest request);

	List<GbltOrgMst> getOrgList(HttpServletRequest request);

}
