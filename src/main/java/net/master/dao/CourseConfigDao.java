package net.master.dao;
 

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.model.master.pojo.IYFCourseConfig;

@Repository
@Transactional
public interface CourseConfigDao extends JpaRepository<IYFCourseConfig,Long> {
 
    @Query("SELECT e from IYFCourseConfig e where e.mIsValid = :name ") 
    Page<IYFCourseConfig> findAllByIsvalid(@Param("name") Integer name, Pageable pageable);

    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber and e.IIsValid = 1") 
   	List<Object> getStudentByMobile(@Param("mobileNumber") Long mobileNumber);

    @Query("SELECT e from IYFCourseConfig e where e.mIsValid = 1") 
	List<IYFCourseConfig> getCourseConfigList();


    @Query("SELECT e from IYFCourseConfig e where e.mICourseConfig = :name") 
	List<IYFCourseConfig> getCourseConfigById(@Param("name") Long id);
 
	 
}
