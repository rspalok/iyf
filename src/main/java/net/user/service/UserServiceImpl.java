package net.user.service;
 
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.GbltUsersRolesTrn;
import net.model.bean.CrmUser;
import net.user.dao.UserDao;
 

@Service
public class UserServiceImpl implements UserService{

	// need to inject user dao
	@Autowired
	private UserDao userDao;
 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public GbltUserMst findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser,GbltOtpStudentRegTrn c_user) {
		 
		GbltUserMst user = new GbltUserMst();
		 // assign user details to the user object
		user.setStUserName(crmUser.getUserName());
		user.setStPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setIUserId(crmUser.getRegistrationId());
		user.setStFirstName(c_user.getFirstName());
		user.setStLastName(c_user.getLastName());
		user.setStEmail(c_user.getEmail());
		user.setDtEntry(new Date()); 
		user.setIIsValid(1);
		user.setStOrgId(crmUser.getOrgName());
		// give user default role of "employee"
		System.out.println("====IYF20230204422  "+user);
		//user.setRoles(Arrays.asList(roleDao.findRoleByName(crmUser.getRoleName())));

		// save user in the database
		userDao.save(user);
		
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
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		GbltUserMst user = userDao.findByUserName(userName); 
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new MyUserDetailsService(user); 
	}

 

	@Override
	public GbltOtpStudentRegTrn findByregistrationId(String registrationId) {
		// TODO Auto-generated method stub
		return userDao.findByregistrationId(registrationId,"PNB108");
	}
}
