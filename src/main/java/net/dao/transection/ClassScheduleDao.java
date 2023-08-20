package net.dao.transection;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.transection.IyfClassSchedTrn;
import net.model.transection.IyfClassSchedTrnPk;

public interface ClassScheduleDao extends JpaRepository<IyfClassSchedTrn, IyfClassSchedTrnPk> {


    @Query("SELECT e from IyfClassSchedTrn e JOIN e.ObjIYFCourseConfig where e.mIsValid = :name and e.mStOrgId =:org") 
    Page<IyfClassSchedTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org") String mStOrgId);

    @Query("SELECT e from IyfClassSchedTrn e where e.mICourseConfig=:name and e.mIsValid = 1 and e.mStOrgId =:org and " 
    		+ " month(e.mDtschedule)=:month  and year(e.mDtschedule)=:year and day(e.mDtschedule) =:day "

    		+ "") 
	List<IyfClassSchedTrn> getClassScheduleList(@Param("name") Long id, @Param("day") int day, @Param("month") int month, @Param("year") int year,@Param("org") String mStOrgId);

    @Query("SELECT e from IyfClassSchedTrn e where e.mICourseConfig=:name2 and e.mIClassId=:name and e.mStOrgId =:org") 
	IyfClassSchedTrn getClassScheduleById(@Param("name") Long mIClassId, @Param("name2") Long mICourseConfig,@Param("org") String mStOrgId);

    @Query("SELECT e from IyfClassSchedTrn e where e.mICourseConfig=:name and  e.mStOrgId =:org ") 
	List<IyfClassSchedTrn> getCourseClassScheduleById(@Param("name") Long mICourseConfig,@Param("org") String orgid);

    @Query("SELECT e from IyfClassSchedTrn e JOIN e.ObjIYFCourseConfig "
    		+ " where e.mICourseConfig=:courseId and e.mStOrgId =:org  and e.mIsValid =1 ") 
    List<IyfClassSchedTrn> getAllClassLst(@Param("courseId") Long courseId,@Param("org") String org);

   // @Query("SELECT e from IyfClassSchedTrn e JOIN e.ObjIYFCourseConfig where e.mICourseConfig IN (:courseSet) e.mIsValid =1 and e.mStOrgId =:org") 
   // List<IyfClassSchedTrn> getClassListOfCourseList(@Param("courseSet") ArrayList<Long> ids,@Param("org") String org);
}
