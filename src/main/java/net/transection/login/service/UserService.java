package net.transection.login.service;
 
import javax.servlet.http.HttpServletRequest;

import net.model.bean.CrmUser;
import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.role.GbltUserMst;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

public interface UserService {

	public GbltUserMst findByUserUserId(String userName);
	public GbltUserMst findByUserNames(String userName);

    void save(CrmUser crmUser, GbltOtpStudentRegTrn user);

	GbltOtpStudentRegTrn findByregistrationId(String registrationId,HttpServletRequest objRequest_p);

	public void resetPassword(CrmUser theCrmUser, HttpServletRequest request);
	public GbltOrgMst allOrgDetails(GbltUserMst gbltUserMst); 

}
