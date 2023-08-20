package net.dao.transection;
 

import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.GbltOtpStudentRegTrn;
import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCoureRegTrnPk;
import net.model.transection.IyfCourseAttenTrn;

public interface CourseRegistrationDao extends JpaRepository<IyfCoureRegTrn,IyfCoureRegTrnPk> {
	
	
	@Query("SELECT s FROM IyfCoureRegTrn s JOIN s.objGbltOtpStudentRegTrns Join s.ObjIYFCourseConfig where s.mICourseConfig =:mICourseConfig and s.mIsValid = 1 and s.mStOrgId =:org") 
	List<IyfCoureRegTrn> getCourseRegValidStudentObj(@Param("mICourseConfig") Long id,@Param("org") String mStOrgId);


	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig  and s.stStudentId = :mStudentId and s.mStOrgId =:org" ) 
	IyfCoureRegTrn getRegStudentForAttendance(@Param("mStudentId") String mStudentId,@Param("mICourseConfig")  Long mICourseConfig,@Param("org") String mStOrgId);

//DEMO
	@Query("SELECT s FROM IyfCoureRegTrn s JOIN s.objGbltOtpStudentRegTrns where s.mICourseConfig =:mICourseConfig  and s.mIsValid = 1 and s.mStOrgId =:org")  
	List<IyfCoureRegTrn> getAllStudentId(@Param("mICourseConfig") Long mICourseConfig,@Param("org") String mStOrgId);
	
	@Query("SELECT s FROM GbltOtpStudentRegTrn s where s.stStudentId in :studentIds and s.stOrgId =:org")//and s.IIsValid = 1
	List<GbltOtpStudentRegTrn> getAllOtpStudentIds(@Param("studentIds") List<String> studentIds,@Param("org") String mStOrgId);


	@Query("SELECT s FROM IyfCourseAttenTrn s JOIN s.ObjIYFCourseConfig JOIN s.objGbltOtpStudentRegTrns where s.mICourseConfig =:mICourseConfig and s.mIClassId=:mIClassId and s.mIsValid = 1 and s.mStOrgId =:org") 
	List<IyfCourseAttenTrn> getAllPresentStudentId(Long mICourseConfig, Long mIClassId,@Param("org") String mStOrgId);


	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig and s.stStudentId=:stStudentId and s.mIsValid = 1 and s.mStOrgId =:org")  
	List<IyfCoureRegTrn> checkIsStudentRegisterdForCourse(@Param("mICourseConfig") Long getmICourseConfig,@Param("stStudentId")  String stStudentId,@Param("org") String mStOrgId);

	@Query("SELECT COUNT(s) FROM IyfCoureRegTrn s "
			+ " where s.mICourseConfig =:mICourseConfig  "
    		+ " and s.mStOrgId =:orgid " 
			+ "")//and s.IIsValid = 1 
	Integer getTotalRegCount(@Param("mICourseConfig") Long mICourseConfig,@Param("orgid")  String orgid);

	@Query("SELECT s FROM IyfCoureRegTrn s JOIN s.objGbltOtpStudentRegTrns where s.stStudentId =:stStudentId "
			+ " and s.mIsValid = 1 and s.mStOrgId =:org")  
	List<IyfCoureRegTrn> getCourseInrolledListbyStudentId(@Param("stStudentId") String stStudentId, @Param("org") String org);

 
 
}
