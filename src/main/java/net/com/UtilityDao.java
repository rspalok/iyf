package net.com;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.model.master.GbltOrgMst;
import net.model.master.GbltRolMst;

public interface UtilityDao extends JpaRepository<GbltOrgMst, String> {

	@Query("SELECT e from GbltRolMst e ") 
	List<GbltRolMst> getAllRoleDetails();

    //@Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber") 
}
