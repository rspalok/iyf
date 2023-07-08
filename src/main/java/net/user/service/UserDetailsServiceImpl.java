package net.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.model.master.GbltUserMst;
import net.user.dao.UserRepository;

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
