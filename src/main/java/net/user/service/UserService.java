package net.user.service;
 
import net.model.bean.CrmUser;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltUserMst;

public interface UserService {

	public GbltUserMst findByUserNamess(String userName);

    void save(CrmUser crmUser, GbltOtpStudentRegTrn user);

	GbltOtpStudentRegTrn findByregistrationId(String registrationId); 

}
