package net.user.dao;
 

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.GbltUsersRolesTrn;

public interface UserDao {

	//@Query("SELECT u FROM GbltUserMst u WHERE u.username = :username")
	public GbltUserMst findByUserName(@Param("username") String userName);
    
	public void save(GbltUserMst user);

	public GbltOtpStudentRegTrn findByregistrationId(String registrationId, String string);

	public void saveTrn(GbltUsersRolesTrn userTrn);

	public GbltUsersRolesTrn findByUserDtlsName(String theUserName);
    
}
