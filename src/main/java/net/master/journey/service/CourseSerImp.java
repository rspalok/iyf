package net.master.journey.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import net.com.utilityService;
import net.master.journey.dao.CourseDao;
import net.model.bean.GbltUserBean;
import net.model.master.pojo.course.IyfCourseMst;
import net.model.master.pojo.org.GbltOrgMst;

@Transactional
@Service
public class CourseSerImp implements CourseSer {
	
	@Autowired
	public CourseDao dao;
	
	@Autowired
	public utilityService utilService;

	@Override
	public Page<IyfCourseMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.dao.findAllByIsvalid( 1,pageable,org);
		//return this.dao.findAll(pageable);
	}

	@Override
	public void saveIyfCourseMst(IyfCourseMst iyfCourseMst, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		
		iyfCourseMst.setmDtEntry(new Date());
		iyfCourseMst.setmIsValid(1);
		iyfCourseMst.setmStOwnerId(theUser.getIUserId());
		iyfCourseMst.setmStOrgId(org);
		if(iyfCourseMst.getmICourse()==null) { 
			iyfCourseMst.setmICourse((int)dao.count()+1);
		}
		dao.save(iyfCourseMst);
		
	}

	@Override
	public IyfCourseMst getOrgUnitById(Integer id,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		Optional<IyfCourseMst> optional = dao.findById(id,org);
		IyfCourseMst batch = null;
		if (optional.isPresent()) {
			batch = optional.get();
		} else {
			throw new RuntimeException(" mOrgUnit not found for id :: " + id);
		}
		return batch;
	}

	@Override
	public void deleteOrgUnitById(IyfCourseMst batch,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		batch.setmIsValid(0);
		batch.setmStOwnerId(theUser.getIUserId());
		batch.setmStOrgId(org);
		this.dao.save(batch);
	}

	@Override
	public List<GbltOrgMst> getOrgList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		return utilService.getAllOrgDetails();
	}

}
