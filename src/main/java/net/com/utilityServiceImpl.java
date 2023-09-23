package net.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.dao.master.CourseConfigDao;
import net.model.master.GbltOrgMst;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltRolMst;
import net.model.master.GbltUserMst;
import net.model.master.IYFBatchMst;
import net.model.master.IyfCourseMst;
import net.model.transection.IYFCourseConfig;

@Service
@Transactional
public class utilityServiceImpl implements utilityService {

	@Autowired
	public CourseConfigDao ccfdao;
	
	@Autowired UtilityDao dao;
	@Override
	public List<GbltOrgMst> getAllOrgDetails() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public List<GbltRolMst> getAllRoleDetails() {
		
		//HttpSession session = request.getSession(); 
		//Object theUser = session.getAttribute("user");
		//System.out.println("$$$$$$$$$$$$$$$S"+theUser);  
		//employee.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
		//employee.setStOrgId(String.valueOf(((GbltUserMst) theUser).getstOrgId()));
		 
		// TODO Auto-generated method stub
		return dao.getAllRoleDetails();
	}
	@Override
	public List<IYFBatchMst> getBatchList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<IyfCourseMst> getCourseList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		return ccfdao.getCourseConfigList(org);
	}
	@Override
	public String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
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
	public String studentDetailsByStudentId(String stStudentId,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TODO Auto-generated method stub
		List<GbltOtpStudentRegTrn> list = dao.studentByStudentId(stStudentId,org);
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
	public List<GbltOtpStudentRegTrn> studentByStudentId(String stStudentId, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		return dao.studentByStudentId(stStudentId,org);
	}

	@Override
	public List<GbltOtpStudentRegTrn> getStudentListByMobile(Long mobileNumber, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TODO Auto-generated method stub
		List<GbltOtpStudentRegTrn> list = dao.getStudentListByMobile(mobileNumber,org);
		System.out.println("====== "+list);
		return list;
	}
}
