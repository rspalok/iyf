package net.com.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.com.dao.UtilityDao;
import net.model.GbltRolMst;
import net.model.GbltUserMst;
import net.model.master.pojo.GbltOrgMst;

@Service
@Transactional
public class utilityServiceImpl implements utilityService {

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

}
