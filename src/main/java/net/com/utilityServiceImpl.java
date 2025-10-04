package net.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.master.course.dao.CourseConfigDao;
import net.model.bean.GbltUserBean;
import net.model.master.pojo.batch.IYFBatchMst;
import net.model.master.pojo.classtype.BlackListTypeMst;
import net.model.master.pojo.course.IyfCourseMst;
import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.org.GbltOrgUnitMst;
import net.model.master.pojo.role.GbltRolMst;
import net.model.transection.pojo.courseconfig.IYFCourseConfig;
import net.model.transection.pojo.notice.IyfPublicRegUrlTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

@Service
@Transactional
public class utilityServiceImpl implements utilityService {

	@Autowired
	public CourseConfigDao ccfdao;
	
	@Autowired UtilityDao dao;
	@Override
	public List<GbltOrgMst> getAllOrgDetails() {
		// TO DO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public List<GbltRolMst> getAllRoleDetails() {
		
		//HttpSession session = request.getSession(); 
		//Object theUser = session.getAttribute("user");
		//System.out.println("$$$$$$$$$$$$$$$S"+theUser);  
		//gbltOtpStudentRegTrn.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
		//gbltOtpStudentRegTrn.setStOrgId(String.valueOf(((GbltUserMst) theUser).getstOrgId()));
		 
		// TO DO Auto-generated method stub
		return dao.getAllRoleDetails();
	}
	@Override
	public List<IYFBatchMst> getBatchList(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		return null;
	}
	@Override
	public List<IyfCourseMst> getCourseList(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		return null;
	}
	@Override
	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		return ccfdao.getCourseConfigList(org);
	}
	@Override
	public String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TO DO Auto-generated method stub
		List<Object> list = dao.getStudentByMobile(mobileNumber,org);
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TO DO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	@Override
	public String studentDetailsByStudentId(String stStudentId,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TO DO Auto-generated method stub
		List<GbltOtpStudentRegTrn> list = dao.studentByStudentId(stStudentId,org);
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TO DO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	@Override
	public List<GbltOtpStudentRegTrn> studentByStudentId(String stStudentId, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		return dao.studentByStudentId(stStudentId,org);
	}

	@Override
	public List<GbltOtpStudentRegTrn> getStudentListByMobile(Long mobileNumber, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TO DO Auto-generated method stub
		List<GbltOtpStudentRegTrn> list = dao.getStudentListByMobile(mobileNumber,org);
		System.out.println("====== "+list);
		return list; 
	}
	@Override
	public List<GbltOrgUnitMst> allOrgUnits(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object theUser = session.getAttribute("user");
		GbltUserBean obj = (GbltUserBean) theUser;
		return dao.allOrgUnits(obj.getStOrgId());
	}
	@Override
	public List<IyfPublicRegUrlTrn> getUrlList(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession();
		Object theUser = session.getAttribute("user");
		GbltUserBean obj = (GbltUserBean) theUser;
		return dao.getUrlList(obj.getStOrgId());
	}
	@Override
	public List<BlackListTypeMst> getBlackListType(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession();
		Object theUser = session.getAttribute("user");
		GbltUserBean obj = (GbltUserBean) theUser;
		return dao.getBlackListType(obj.getStOrgId());
	}
}
