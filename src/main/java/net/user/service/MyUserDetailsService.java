package net.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.model.master.GbltRolMst;
import net.model.master.GbltUserMst;

public class MyUserDetailsService implements UserDetails {

	private static final long serialVersionUID = 1L;
	private GbltUserMst user;
    
    public MyUserDetailsService(GbltUserMst user) {
        this.user = user;
    }
  
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GbltRolMst> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (GbltRolMst role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getStName()));
		}
		System.out.println("==authorities=="+authorities);
		return authorities;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getStPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getStUserName();//user.getStFirstName()+" "+user.getStLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		boolean isEnabled=false;
		if(user.getIIsValid()==1) {
			isEnabled=true;
		}
		return isEnabled;
	}
	
	public String userFirstName() {
		// TODO Auto-generated method stub
		return user.getStFirstName();
	}

}
