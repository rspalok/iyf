package net.com;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.GbltOrgMst;
import net.model.master.GbltOrgUnitMst;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltRolMst;

public interface UtilityDao extends JpaRepository<GbltOrgMst, String> {

	@Query("SELECT e from GbltRolMst e ") 
	List<GbltRolMst> getAllRoleDetails();

	 @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber and e.IIsValid = 1 and e.stOrgId =:org") 
	 List<Object> getStudentByMobile(@Param("mobileNumber") Long mobileNumber,@Param("org") String org);
	 
	 
	 @Query("SELECT e from GbltOtpStudentRegTrn e where e.stStudentId = :stStudentId and e.IIsValid = 1 and e.stOrgId =:org")
	 List<GbltOtpStudentRegTrn> studentByStudentId(@Param("stStudentId") String stStudentId,@Param("org") String org);

	 @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber and e.IIsValid = 1 and e.stOrgId =:org") 
	 List<GbltOtpStudentRegTrn> getStudentListByMobile(@Param("mobileNumber") Long mobileNumber,@Param("org") String org);
	 
    //@Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber") 
	 @Query("SELECT e from GbltOrgUnitMst e where e.stOrgId =:stOrgId ") 
	 List<GbltOrgUnitMst> allOrgUnits(@Param("stOrgId") String stOrgId);
}
