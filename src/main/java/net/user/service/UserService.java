package net.user.service;
 
import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.bean.CrmUser;

public interface UserService {

	public GbltUserMst findByUserNamess(String userName);

    void save(CrmUser crmUser, GbltOtpStudentRegTrn user);

	GbltOtpStudentRegTrn findByregistrationId(String registrationId); 

}
