package net.transection.dao;
 

import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.GbltOtpStudentRegTrn;
import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCoureRegTrnPk;
import net.model.transection.IyfCourseAttenTrn;

public interface CourseRegistrationDao extends JpaRepository<IyfCoureRegTrn,IyfCoureRegTrnPk> {
	
	//@Query("SELECT new com...dto.DeptEmpDto(d.name, e.name, e.email, e.address) FROM Department d LEFT JOIN d.employees e") 
	String queryss="SELECT e.stStudentId as stStudentId, "
			+ " s.firstName as firstName, "
			+ " s.IMobile as IMobile, "
			+ " e.mICourseConfig as mICourseConfig " 
			+ " from IyfCoureRegTrn e , GbltOtpStudentRegTrn s where e.mIsValid = 1 "
			+ "and e.mICourseConfig =:mICourseConfig and  e.stStudentId = s.stStudentId ";
	
	@Query(queryss) 
	List<Object> getCourseRegValidStudent(@Param("mICourseConfig") Long id);

	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig and s.mIsValid = 1") 
	List<IyfCoureRegTrn> getCourseRegValidStudentObj(@Param("mICourseConfig") Long id);


	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig  and s.stStudentId = :mStudentId") 
	IyfCoureRegTrn getRegStudentForAttendance(@Param("mStudentId") String mStudentId,@Param("mICourseConfig")  Long mICourseConfig);


	@Query("SELECT s FROM IyfCoureRegTrn s where s.mICourseConfig =:mICourseConfig  and s.mIsValid = 1")  
	List<IyfCoureRegTrn> getAllStudentId(@Param("mICourseConfig") Long mICourseConfig);
	
	@Query("SELECT s FROM GbltOtpStudentRegTrn s where s.stStudentId in ?1")//and s.IIsValid = 1
	List<GbltOtpStudentRegTrn> getAllOtpStudentIds(List<String> studentIds);


	@Query("SELECT s FROM IyfCourseAttenTrn s where s.mICourseConfig =:mICourseConfig and s.mIClassId=:mIClassId and s.mIsValid = 1") 
	List<IyfCourseAttenTrn> getAllPresentStudentId(Long mICourseConfig, Long mIClassId);
 
 
}
