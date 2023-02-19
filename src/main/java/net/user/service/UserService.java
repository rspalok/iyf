package net.user.service;
 
import org.springframework.security.core.userdetails.UserDetailsService;
 

import net.model.GbltUserMst;
import net.model.bean.CrmUser;

public interface UserService extends UserDetailsService {

    GbltUserMst findByUserName(String userName);

    void save(CrmUser crmUser);
}
