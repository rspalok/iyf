package net.user.service;
 
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.bean.CrmUser;

public interface UserService extends UserDetailsService {

	public GbltUserMst findByUserName(String userName);

    void save(CrmUser crmUser, GbltOtpStudentRegTrn user);

	GbltOtpStudentRegTrn findByregistrationId(String registrationId);

}
