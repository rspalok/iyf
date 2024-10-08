package net.transection.yatra.service;

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
import com.fasterxml.jackson.databind.SerializationFeature;

import net.master.facilitator.dao.FacilitatoDao;
import net.model.bean.GbltUserBean;
import net.model.bean.YatraBean;
import net.model.master.pojo.facilitator.IYFFacilitatorMst;
import net.model.transection.pojo.yatra.YatraCruiseTrn;
import net.model.transection.pojo.yatra.YatraRegTrn;
import net.transection.yatra.dao.YatraMngDao;

@Service
@Transactional
public class YatraMngSerImp implements YatraMngSer {


	@Autowired 
	public YatraMngDao dao;
	

	@Autowired 
	public FacilitatoDao fdao;
	
	@Override
	public Page<YatraCruiseTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); 
		return this.dao.findAllByIsvalid( 1,pageable,org);
		//return null;
	}

	@Override
	public List<YatraCruiseTrn> getYatraCruiseById(Long yatraCruiseId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		return dao.getYatraCruiseById(yatraCruiseId,org);
	}

	@Override
	public void registerStudentForTheYatra(YatraBean yatraBean, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
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
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		return dao.yatraRagisterdList(yatraCruiseId,org);
	}

	@Override
	public List<IYFFacilitatorMst> getfacilitatorList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return fdao.findAll();
	}

	@Override
	public String studentByMobileNofromYatraTable(String StudentId, Long yatraCruiseId,HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TODO Auto-generated method stub
		List<Object> list = dao.studentByMobileNofromYatraTable(StudentId,yatraCruiseId,org);
		System.out.println(list.toString());
		ObjectMapper mapper = new ObjectMapper();

		

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String data = null;
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return data;
			
	}

}
