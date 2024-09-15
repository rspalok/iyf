package net.transection.decision.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.model.bean.GbltUserBean;
import net.model.transection.bean.GbltStudentRegBean;
import net.model.transection.pojo.BlockedStudent;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.transection.registration.dao.BlockedStudentRepository;
import net.transection.registration.dao.GbltOtpStudentRegTrnRepository;

@Service
public class DecisionServiceImpl  implements DecisionService {

	@Autowired
	private GbltOtpStudentRegTrnRepository dao;
	
	@Autowired
	private BlockedStudentRepository rDao;
	
	
	@Override
	public Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, HttpServletRequest objRequest_p) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return this.dao.findAllByIsvalid(0,pageable,org);
	} 

	@Override
	public List<GbltOtpStudentRegTrn> allCurrentRegStudent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String Org=theUser.getStOrgId(); 
		return this.dao.getAllValidStudent(0, Org);
	}

	@Override
	public void saveGbltOtpStudentRegTrn(GbltStudentRegBean gbltStudentRegBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String Org=theUser.getStOrgId(); 

		GbltOtpStudentRegTrn stu = dao.getStudentByIds(gbltStudentRegBean.getStStudentId(), Org);
		stu.setDtEntry(new Date());
		stu.setStOwnerId(theUser.getIUserId());
		System.out.println("========"+gbltStudentRegBean.getRemarks());
		if(gbltStudentRegBean.getIIsValid()==0) {
			//delete all attendance
			//delete all registration
			//delete student record
			stu.setIIsValid(0);
			dao.save(stu);
			BlockedStudent blockedStudent=new BlockedStudent();
			blockedStudent.setDtEntry(new Date());
			blockedStudent.setIIsValid(1);
			blockedStudent.setmBlackListType(gbltStudentRegBean.getmBlackListType());
			blockedStudent.setRemarks(gbltStudentRegBean.getRemarks());
			blockedStudent.setStStudentId(gbltStudentRegBean.getStStudentId());
			blockedStudent.setStOwnerId(theUser.getIUserId());
			blockedStudent.setStOrgId(Org);
			rDao.save(blockedStudent);
		}
		if(gbltStudentRegBean.getIIsValid()==1) {
			stu.setIMobile(gbltStudentRegBean.getIMobile());
			
			dao.save(stu);
		}
		
	}

}
