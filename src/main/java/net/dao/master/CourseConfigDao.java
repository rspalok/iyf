package net.dao.master;
 

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.pojo.IYFCourseConfig;
import net.model.master.pojo.IYFCourseConfigPk;

@Transactional
public interface CourseConfigDao extends JpaRepository<IYFCourseConfig,IYFCourseConfigPk> {
 
    @Query("SELECT e from IYFCourseConfig e JOIN e.iyfCourseMst Join e.iyfBatchMst where  e.mIsValid = :name and e.mStOrgId =:org") 
    Page<IYFCourseConfig> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org")  String org);

    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber and e.IIsValid = 1 and e.stOrgId =:org") 
   	List<Object> getStudentByMobile(@Param("mobileNumber") Long mobileNumber,@Param("org") String org);

    @Query("SELECT e from IYFCourseConfig e JOIN e.iyfCourseMst Join e.iyfBatchMst where e.mIsValid = 1 and e.mStOrgId =:org") 
	List<IYFCourseConfig> getCourseConfigList(@Param("org") String org);


    @Query("SELECT e from IYFCourseConfig e where e.mICourseConfig = :name and e.mStOrgId =:org") 
	List<IYFCourseConfig> getCourseConfigById(@Param("name") Long id,@Param("org") String org);
 
	 
}
