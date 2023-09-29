package net.service.master;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.dao.master.BatchDao;
import net.dao.master.CourseConfigDao;
import net.dao.master.CourseDao;
import net.model.bean.GbltUserBean;
import net.model.master.ClassTypeMst;
import net.model.master.IYFBatchMst;
import net.model.master.IyfCourseMst;
import net.model.transection.IYFCourseConfig;  

@Transactional
@Service
public class CourseConfigSerImp implements CourseConfigSer {
	
	@Autowired
	public CourseConfigDao dao;

	
	@Autowired
	public BatchDao bdao;

	@Autowired 
	public CourseDao cdao;
	
	
	@Override
	public Page<IYFCourseConfig> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); 
		return this.dao.findAllByIsvalid( 1,pageable,org);
		//return this.dao.findAll(pageable);
	}

	@Override
	public void saveIYFCourseConfig(IYFCourseConfig batch, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		batch.setmDtEntry(new Date());
		batch.setmIsValid(1);
		batch.setmStOwnerId(theUser.getIUserId());
		batch.setmStOrgId(org);
		if(batch.getmICourseConfig()==null) { 
			batch.setmICourseConfig(dao.count()+1);
		}
		dao.save(batch);
	}

	@Override
	public IYFCourseConfig getCourseConfigById(Long id,HttpServletRequest request) {
		System.out.println("=======getCourseConfigById===== "+id);
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		List<IYFCourseConfig> optional = dao.getCourseConfigById(id,org);
		 
		return optional.get(0);
	}

	@Override
	public void deleteCourseConfigById(IYFCourseConfig batch,HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		batch.setmStOwnerId(theUser.getIUserId());
		batch.setmStOrgId(org);
		batch.setmIsValid(0);
		this.dao.save(batch);
	}

	@Override
	public List<IYFBatchMst> getBatchList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession(); 
		//GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		//String org= theUser.getStOrgId();
		return this.bdao.findAll();
	}

	@Override
	public List<IyfCourseMst> getCourseList(HttpServletRequest request) {
		//HttpSession session = request.getSession(); 
		//GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		//String org= theUser.getStOrgId();
		// TODO Auto-generated method stub
		return this.cdao.findAll();
	}

	@Override
	public String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TODO Auto-generated method stub
		List<Object> list = dao.getStudentByMobile(mobileNumber,org);
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		return dao.getCourseConfigList(org);
	}

	@Override
	public List<ClassTypeMst> getClassTypeMstList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		System.out.println( org);
		return dao.getClassTypeMstList(org);
	}

	

	

}
