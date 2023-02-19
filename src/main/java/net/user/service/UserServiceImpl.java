package net.user.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.model.GbltRolMst;
import net.model.GbltUserMst;
import net.model.bean.CrmUser;
import net.user.dao.RoleDao;
import net.user.dao.UserDao;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
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
	public void save(CrmUser crmUser) {
		GbltUserMst user = new GbltUserMst();
		 // assign user details to the user object
		user.setstUserName(crmUser.getUserName());
		user.setstPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setstFirstName(crmUser.getFirstName());
		user.setstLastName(crmUser.getLastName());
		user.setstEmail(crmUser.getEmail());

		// give user default role of "employee"
		user.setstRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		GbltUserMst user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getstUserName(), user.getstPassword(),
				mapRolesToAuthorities(user.getstRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<GbltRolMst> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getstName())).collect(Collectors.toList());
	}
}
