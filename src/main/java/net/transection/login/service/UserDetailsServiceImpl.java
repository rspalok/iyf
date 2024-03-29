package net.transection.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.model.master.pojo.role.GbltUserMst;
import net.transection.login.dao.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userDao;
 
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		GbltUserMst user = userDao.getUserByUsername(userName); 
		System.out.println("==========="+user);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new MyUserDetailsService(user);
	}
	
	
}
