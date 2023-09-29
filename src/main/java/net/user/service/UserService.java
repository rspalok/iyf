package net.user.service;
 
import javax.servlet.http.HttpServletRequest;

import net.model.bean.CrmUser;
import net.model.master.GbltOrgMst;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltUserMst;

public interface UserService {

	public GbltUserMst findByUserNamess(String userName);
	public GbltUserMst findByUserNames(String userName);

    void save(CrmUser crmUser, GbltOtpStudentRegTrn user);

	GbltOtpStudentRegTrn findByregistrationId(String registrationId,HttpServletRequest objRequest_p);

	public void resetPassword(CrmUser theCrmUser, HttpServletRequest request);
	public GbltOrgMst allOrgDetails(GbltUserMst gbltUserMst); 

}
