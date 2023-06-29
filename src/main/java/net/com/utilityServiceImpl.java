package net.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dao.master.CourseConfigDao;
import net.model.GbltRolMst;
import net.model.GbltUserMst;
import net.model.master.pojo.GbltOrgMst;
import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFCourseConfig;
import net.model.master.pojo.IyfCourseMst;

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

}
