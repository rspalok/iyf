package net.dao.transection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.transection.IyfCourseAttenTrn;
import net.model.transection.IyfCourseAttenTrnPk;

public interface CourseAttenTrnDao extends JpaRepository<IyfCourseAttenTrn, IyfCourseAttenTrnPk>{


	@Query("SELECT s FROM IyfCourseAttenTrn s JOIN s.objGbltOtpStudentRegTrns "
			+ " JOIN s.ObjIYFCourseConfig JOIN s.objIyfClassSchedTrn "
			+ " where s.isPresent=1 and s.mStOrgId =:org and s.mIClassId=:classId and s.mICourseConfig=:mICourseConfig and "
    		+ " month(s.mDtEntry)=:month  and year(s.mDtEntry)=:year and day(s.mDtEntry) =:day " 
			+ "")//and s.IIsValid = 1
	List<IyfCourseAttenTrn> getAllPresentStudent(@Param("mICourseConfig") Long mICourseConfig,@Param("classId") Long classId, @Param("day") int day, @Param("month") int month, @Param("year") int year,@Param("org") String mStOrgId);

	
	@Query("SELECT s FROM IyfCourseAttenTrn s JOIN s.objGbltOtpStudentRegTrns"
			+ " JOIN s.ObjIYFCourseConfig JOIN s.objIyfClassSchedTrn "
			+ " where s.mICourseConfig =:mICourseConfig and "
    		+ " s.mIClassId=:mIClassId and s.isPresent=1 and s.mStOrgId =:orgid " 
			+ "")//and s.IIsValid = 1 
	List<IyfCourseAttenTrn> getAllPresentStudentInClass(@Param("mICourseConfig") Long mICourseConfig,@Param("mIClassId")  Long mClassId,@Param("orgid")  String orgid);

	@Query("SELECT COUNT(s) FROM IyfCourseAttenTrn s "
			+ " where s.mICourseConfig =:mICourseConfig and "
    		+ " s.mIClassId=:mIClassId and s.isPresent=1 and s.mStOrgId =:orgid " 
			+ "")//and s.IIsValid = 1 
	Integer getTotalPresentCount(@Param("mICourseConfig") Long mICourseConfig,@Param("mIClassId")  Long mClassId,@Param("orgid")  String orgid);

}
