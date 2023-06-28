package net.user.dao;
 

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.GbltUsersRolesTrn;

public interface UserDao {

	public GbltUserMst findByUserName(String userName);
    
    void save(GbltUserMst user);

	GbltOtpStudentRegTrn findByregistrationId(String registrationId, String string);

	void saveTrn(GbltUsersRolesTrn userTrn);
    
}
