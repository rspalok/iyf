package net.master.org.service;

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
import net.master.org.dao.OrgDao;
import net.model.bean.GbltUserBean;
import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.org.GbltOrgUnitMst;

@Transactional
@Service
public class OrgSerImp implements OrgSer {
	
	@Autowired
	public OrgDao dao;
	
	@Autowired
	public utilityService utilService;

	@Override
	public Page<GbltOrgUnitMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest objRequest_p) {
		// TO DO Auto-generated method stub
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
	public void saveGbltOrgUnitMst(GbltOrgUnitMst gbltOrgUnitMst, HttpServletRequest request, HttpServletResponse response) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		
		gbltOrgUnitMst.setDtEntry(new Date());
		gbltOrgUnitMst.setIIsValid(1);
		gbltOrgUnitMst.setStOwnerId(theUser.getIUserId());
		gbltOrgUnitMst.setStOrgId(org);
		if(gbltOrgUnitMst.getmOrgUnit()==null) { 
			gbltOrgUnitMst.setmOrgUnit((int)dao.count()+1);
		}
		dao.save(gbltOrgUnitMst);
		
	}

	@Override
	public GbltOrgUnitMst getOrgUnitById(Integer id,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		Optional<GbltOrgUnitMst> optional = dao.findById(id,org);
		GbltOrgUnitMst batch = null;
		if (optional.isPresent()) {
			batch = optional.get();
		} else {
			throw new RuntimeException(" mOrgUnit not found for id :: " + id);
		}
		return batch;
	}

	@Override
	public void deleteOrgUnitById(GbltOrgUnitMst batch,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		batch.setIIsValid(0);
		batch.setStOwnerId(theUser.getIUserId());
		batch.setStOrgId(org);
		this.dao.save(batch);
	}

	@Override
	public List<GbltOrgMst> getOrgList(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		
		return utilService.getAllOrgDetails();
	}

}
