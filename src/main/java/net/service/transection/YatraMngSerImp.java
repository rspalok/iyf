package net.service.transection;

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

import net.dao.transection.YatraMngDao;
import net.model.GbltUserMst;
import net.model.bean.YatraBean;
import net.model.transection.YatraCruiseTrn;
import net.model.transection.YatraRegTrn;

@Service
@Transactional
public class YatraMngSerImp implements YatraMngSer {


	@Autowired 
	public YatraMngDao dao;
	
	@Override
	public Page<YatraCruiseTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); 
		return this.dao.findAllByIsvalid( 1,pageable,org);
		//return null;
	}

	@Override
	public List<YatraCruiseTrn> getYatraCruiseById(Long yatraCruiseId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		return dao.getYatraCruiseById(yatraCruiseId,org);
	}

	@Override
	public void registerStudentForTheYatra(YatraBean yatraBean, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();

		System.out.println("====yatraBean=== "+yatraBean);
		YatraRegTrn yatraRegTrn=new YatraRegTrn();
		yatraRegTrn.setDtEntry(new Date());
		yatraRegTrn.setDtRegistration(new Date());
		yatraRegTrn.setIsValid(1);
		yatraRegTrn.setYatraCruiseId(yatraBean.getYatraCruiseId());
		if(yatraBean.getmContributed()==0) {
			yatraRegTrn.setRegStatus(1);
		}else {
			yatraRegTrn.setRegStatus(2);
		}
		yatraRegTrn.setStOrgId(org);
		yatraRegTrn.setStUserId(theUser.getIUserId());
		yatraRegTrn.setStStudentId(yatraBean.getStStudentId());
		yatraRegTrn.setContributionMode(yatraBean.getContributionMode());
		yatraRegTrn.setContributed(yatraBean.getmContributed());
		System.out.println("======= "+yatraRegTrn);
		dao.save(yatraRegTrn);
	}

	@Override
	public List<YatraRegTrn> yatraRagisterdList(Long yatraCruiseId, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		return dao.yatraRagisterdList(yatraCruiseId,org);
	}

}
