package net.master.journey.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.pojo.course.IyfCourseMst;
import net.model.master.pojo.course.IyfCourseMstPk;
@Transactional 
public interface CourseDao extends JpaRepository<IyfCourseMst,IyfCourseMstPk> {

    @Query("SELECT e from IyfCourseMst e where e.mIsValid = :name and e.mStOrgId=:mStOrgId") 
    Page<IyfCourseMst> findAllByIsvalid(@Param("name") Integer name, Pageable pageable, @Param("mStOrgId") String mStOrgId);

    @Query("SELECT e from IyfCourseMst e where e.mIsValid = 1 and e.mICourse=:mICourse and e.mStOrgId=:mStOrgId") 
	Optional<IyfCourseMst> findById(@Param("mICourse") Integer mICourse, @Param("mStOrgId") String mStOrgId);

}
