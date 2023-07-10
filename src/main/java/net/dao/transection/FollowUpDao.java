package net.dao.transection;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.FollowUpMaster;
import net.model.transection.FollowUpResponsePk;
import net.model.transection.FollowUpResponseTrn;
import net.model.transection.FollowUpTrn;
import net.model.transection.IyfCoureRegTrn;

public interface FollowUpDao extends JpaRepository<FollowUpResponseTrn, FollowUpResponsePk>{
	
	@Query("SELECT s FROM FollowUpMaster s where  s.isValid = 1 and s.stOrgId =:org") 
	List<FollowUpMaster> getFolowUpMstList(@Param("org") String org);
	
	@Query("SELECT s FROM FollowUpTrn s Join s.objFollowUpMaster where  s.isValid = 1 and s.stOrgId =:org") 
	List<FollowUpTrn> getFolowUpTrnList(@Param("org") String org);
	
	@Query("SELECT s FROM IyfCoureRegTrn s where  s.mIsValid = 1 and s.mStOrgId =:org") 
	List<IyfCoureRegTrn> getAllCourseRegisteredStudent(@Param("org") String org);

	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig=:mICourseConfig and s.mIsValid = 1 and s.mStOrgId =:org") 
	List<IyfCoureRegTrn> getAllCourseSpecificRegisteredStudent(@Param("mICourseConfig") Long mICourseConfig,@Param("org") String org);

	@Query("SELECT s FROM FollowUpResponseTrn s where s.followUpId=:followUpId and s.isValid = 1 and s.stOrgId =:org order by s.followUpBy")
	List<FollowUpResponseTrn> getFolowUpStudentList(@Param("followUpId") Integer followUpId, @Param("org") String org);

	@Query("SELECT s FROM FollowUpResponseTrn s Join s.objFollowUpTrn JOIN s.objGbltOtpStudentRegTrns Join s.objGbltOtpStudentRegTrns where  s.isValid = 1 and s.stOrgId =:org and s.followUpId=:followUpId and s.stStudentId =:stStudentId")
	List<FollowUpResponseTrn> getStudentForFolloup(@Param("stStudentId") String stStudentId,@Param("followUpId")  Integer followUpId,@Param("org")  String org);

	@Query("SELECT s FROM FollowUpResponseTrn s where  s.followUpId=:followUpId and s.isValid = 1 and s.stOrgId =:org and s.followUpBy=:user order by s.responseType ,s.objGbltOtpStudentRegTrns.firstName , s.dtEntry ")
	List<FollowUpResponseTrn> getMyFolowUpStudentList(@Param("followUpId") Integer followUpId, @Param("org")String org,@Param("user") String user);

	@Query("SELECT e from FollowUpResponseTrn e JOIN e.objFollowUpTrn where  e.isValid = :name and e.stOrgId =:org order by e.followUpBy , e.responseType ,e.objGbltOtpStudentRegTrns.firstName , e.dtEntry ") 
	Page<FollowUpResponseTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org")  String org);

}
