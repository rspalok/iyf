package net.service.transection;
 
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dao.transection.UserDao;
import net.dao.transection.UserRepository;
import net.model.bean.CrmUser;
import net.model.bean.GbltUserBean;
import net.model.master.GbltOrgMst;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltUserMst;
import net.model.master.GbltUsersRolesTrn;
 

@Service
public class UserServiceImpl implements UserService{

	// need to inject user dao
	@Autowired
	private UserDao userDao;
 
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	//used for session and registration
	public GbltUserMst findByUserUserId(String userName) {
		// check the database if the user already exists
		GbltUserMst user = userDao.findByUserName(userName); 
		return user;
	}
	
	@Override
	@Transactional
	//used for session and registration
	public GbltUserMst findByUserNames(String userName) {
		// check the database if the user already exists
		GbltUserMst user = userDao.findByUserName(userName); 
		return user;
	}
	@Override
	@Transactional
	public void save(CrmUser crmUser,GbltOtpStudentRegTrn c_user) {
		 
		GbltUserMst user = new GbltUserMst(); 
		user.setStUserName(crmUser.getUserName());
		user.setStPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setIUserId(crmUser.getRegistrationId());
		user.setStFirstName(c_user.getFirstName());
		user.setStLastName(c_user.getLastName());
		user.setStEmail(c_user.getEmail());
		user.setDtEntry(new Date()); 
		user.setIIsValid(1);
		user.setmEnabled(true);
		user.setStOrgId(crmUser.getOrgName()); 
		userRepo.save(user);
		GbltUsersRolesTrn userTrn=new GbltUsersRolesTrn();
		userTrn.setIUserId(crmUser.getRegistrationId());
		userTrn.setDtEntry(new Date()); 
		userTrn.setIIsValid(1);
		userTrn.setStOrgId(crmUser.getOrgName());
		userTrn.setIRoleId(crmUser.getRoleName());
		//save data
		userDao.saveTrn(userTrn);
	}


	@Override
	public GbltOtpStudentRegTrn findByregistrationId(String registrationId,HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub		
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");

		return userDao.findByregistrationId(registrationId,obj.getStOrgId());
	}


	@Override
	public void resetPassword(CrmUser theCrmUser, HttpServletRequest request) {
		// TODO Auto-generated method stub
		GbltUserMst user = findByUserUserId(theCrmUser.getUserName()); 
		user.setDtEntry(new Date());
		user.setStPassword(passwordEncoder.encode(theCrmUser.getPassword()));
		 
		userRepo.save(user);
	}

	@Override
	@Transactional
	public GbltOrgMst allOrgDetails(GbltUserMst gbltUserMst) {
		// TODO Auto-generated method stub
		return userDao.allOrgDetails(gbltUserMst.getStOrgId());
	}

}
