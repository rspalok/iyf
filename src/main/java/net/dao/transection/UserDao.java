package net.dao.transection;
 

import org.springframework.data.repository.query.Param;

import net.model.master.GbltOrgMst;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltUserMst;
import net.model.master.GbltUsersRolesTrn;

public interface UserDao {

	//@Query("SELECT u FROM GbltUserMst u WHERE u.username = :username")
	public GbltUserMst findByUserName(@Param("username") String userName);
    
	public void save(GbltUserMst user);

	public GbltOtpStudentRegTrn findByregistrationId(String registrationId, String string);

	public void saveTrn(GbltUsersRolesTrn userTrn);

	public GbltUsersRolesTrn findByUserDtlsName(String theUserName);

	public GbltOrgMst allOrgDetails(String stOrgId);
    
}
