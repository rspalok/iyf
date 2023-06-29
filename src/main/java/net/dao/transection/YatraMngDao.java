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

}
