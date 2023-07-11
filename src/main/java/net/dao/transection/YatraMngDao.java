package net.dao.transection;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.transection.YatraCruiseTrn;
import net.model.transection.YatraRegTrn;
import net.model.transection.YatraRegTrnPk;

public interface YatraMngDao extends JpaRepository<YatraRegTrn,YatraRegTrnPk> {
	
		 
    @Query("SELECT e from YatraCruiseTrn e JOIN e.objYatraSanctuaryMst  where  e.mIsValid = :name and e.mStOrgId =:org") 
    Page<YatraCruiseTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org")  String org);

    @Query("SELECT e from YatraCruiseTrn e JOIN e.objYatraSanctuaryMst  where  e.mIsValid = 1 and e.yatraCruiseId=:yatraCruiseId and e.mStOrgId =:org") 
    List<YatraCruiseTrn> getYatraCruiseById(@Param("yatraCruiseId") Long yatraCruiseId,@Param("org")  String org);
    
    @Query("SELECT e from YatraRegTrn e JOIN e.objYatraCruiseTrn Join e.objGbltOtpStudentRegTrns where  e.isValid = 1 and e.yatraCruiseId=:yatraCruiseId and e.stOrgId =:org") 
	List<YatraRegTrn> yatraRagisterdList(@Param("yatraCruiseId") Long yatraCruiseId,@Param("org")  String org);

    @Query("SELECT e from YatraRegTrn e JOIN e.objYatraCruiseTrn Join e.objGbltOtpStudentRegTrns where e.stStudentId=:stStudentId and e.isValid = 1 and e.yatraCruiseId=:yatraCruiseId and e.stOrgId =:org") 
    List<Object> getStudentBy(@Param("stStudentId") String stStudentId,@Param("yatraCruiseId") Long yatraCruiseId,@Param("org")   String org);

   
    String query2="   select "
    		+ " a.yatraCruiseId as yatraCruiseId, "
    		+ " a.stStudentId as stStudentId, "
    		+ " a.dtRegistration as dtRegistration, "
    		+ " a.regStatus as regStatus, "
    		+ " a.contributionMode as contributionMode, "
    		+ " a.IFacilitator as IFacilitator, "
    		+ " a.contributed as contributed, "
    		+ " b.IMobile as IMobile "
    		+ " from "
    		+ " YatraRegTrn a JOIN a.objGbltOtpStudentRegTrns b "
    		+ " where "
    		+ " a.yatraCruiseId=:yatraCruiseId  "
    		+ " and a.isValid=1  "
    		+ " and a.stStudentId=:stStudentId "
    		+ " and a.stOrgId=:org ";
    @Query("select a from YatraRegTrn a "
    		+ " where a.yatraCruiseId=:yatraCruiseId and a.isValid=1 and a.stStudentId=:stStudentId and a.stOrgId=:org ")
	List<Object> studentByMobileNofromYatraTable(@Param("stStudentId") String studentId, @Param("yatraCruiseId") Long yatraCruiseId, @Param("org") String org);
}
