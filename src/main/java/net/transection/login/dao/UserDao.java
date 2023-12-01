package net.transection.login.dao;
 

import org.springframework.data.repository.query.Param;

import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.role.GbltUserMst;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.model.transection.pojo.role.GbltUsersRolesTrn;

public interface UserDao {

	//@Query("SELECT u FROM GbltUserMst u WHERE u.username = :username")
	public GbltUserMst findByUserName(@Param("username") String userName);
    
	public void save(GbltUserMst user);

	public GbltOtpStudentRegTrn findByregistrationId(String registrationId, String string);

	public void saveTrn(GbltUsersRolesTrn userTrn);

	public GbltUsersRolesTrn findByUserDtlsName(String theUserName);

	public GbltOrgMst allOrgDetails(String stOrgId);
    
}
