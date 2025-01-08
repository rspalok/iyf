package net.transection.attendance.dao;
 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.attendance.IyfCoureRegTrnPk;
import net.model.transection.pojo.attendance.IyfCourseAttenTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

public interface CourseRegistrationDao extends JpaRepository<IyfCoureRegTrn,IyfCoureRegTrnPk> {
	
	
	@Query("SELECT s FROM IyfCoureRegTrn s JOIN s.objGbltOtpStudentRegTrns Join s.ObjIYFCourseConfig where s.mICourseConfig =:mICourseConfig and s.stStudentId not in (select e.stStudentId from IyfCoureRegTrn e where e.mICourseConfig in (:mICourseConfig1))  and s.mIsValid = 1 and s.mStOrgId =:org and s.objGbltOtpStudentRegTrns.IIsValid=1 ") 
	List<IyfCoureRegTrn> getCourseRegValidStudentObj(@Param("mICourseConfig") Long id,@Param("mICourseConfig1") List<Long> exceptId, @Param("org") String mStOrgId);


	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig  and s.stStudentId = :mStudentId and s.mStOrgId =:org and s.objGbltOtpStudentRegTrns.IIsValid=1 " ) 
	IyfCoureRegTrn getRegStudentForAttendance(@Param("mStudentId") String mStudentId,@Param("mICourseConfig")  Long mICourseConfig,@Param("org") String mStOrgId);

//DEMO
	@Query("SELECT s FROM IyfCoureRegTrn s JOIN s.objGbltOtpStudentRegTrns where s.mICourseConfig =:mICourseConfig  and s.mStOrgId =:org and s.objGbltOtpStudentRegTrns.IIsValid=1 ")  
	List<IyfCoureRegTrn> getAllStudentId(@Param("mICourseConfig") Long mICourseConfig,@Param("org") String mStOrgId);
	
	@Query("SELECT s FROM GbltOtpStudentRegTrn s where s.stStudentId in :studentIds and s.stOrgId =:org and s.IIsValid=1 ")//and s.IIsValid = 1
	List<GbltOtpStudentRegTrn> getAllOtpStudentIds(@Param("studentIds") List<String> studentIds,@Param("org") String mStOrgId);


	@Query("SELECT s FROM IyfCourseAttenTrn s JOIN s.ObjIYFCourseConfig JOIN s.objGbltOtpStudentRegTrns where s.mICourseConfig =:mICourseConfig and s.mIClassId=:mIClassId and s.mIsValid = 1 and s.mStOrgId =:org and s.objGbltOtpStudentRegTrns.IIsValid=1 ") 
	List<IyfCourseAttenTrn> getAllPresentStudentId(Long mICourseConfig, Long mIClassId,@Param("org") String mStOrgId);


	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig and s.stStudentId=:stStudentId and s.mIsValid = 1 and s.mStOrgId =:org and s.objGbltOtpStudentRegTrns.IIsValid=1 ")  
	List<IyfCoureRegTrn> checkIsStudentRegisterdForCourse(@Param("mICourseConfig") Long getmICourseConfig,@Param("stStudentId")  String stStudentId,@Param("org") String mStOrgId);

	@Query("SELECT COUNT(s) FROM IyfCoureRegTrn s "
			+ " where s.mICourseConfig =:mICourseConfig  "
    		+ " and s.mStOrgId =:orgid " 
			+ " and s.mIsValid=1 and s.objGbltOtpStudentRegTrns.IIsValid=1 ")//and s.IIsValid = 1 
	Integer getTotalRegCount(@Param("mICourseConfig") Long mICourseConfig,@Param("orgid")  String orgid);

	@Query("SELECT s FROM IyfCoureRegTrn s JOIN s.objGbltOtpStudentRegTrns JOIN s.ObjIYFCourseConfig  where s.stStudentId =:stStudentId "
			+ " and s.mIsValid = 1 and s.mStOrgId =:org and s.objGbltOtpStudentRegTrns.IIsValid=1 ")  
	List<IyfCoureRegTrn> getCourseInrolledListbyStudentId(@Param("stStudentId") String stStudentId, @Param("org") String org);

 
 
}
