package net.transection.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.model.transection.IyfClassSchedTrn;
import net.model.transection.IyfClassSchedTrnPk;

@Repository
public interface ClassScheduleDao extends JpaRepository<IyfClassSchedTrn, IyfClassSchedTrnPk> {


    @Query("SELECT e from IyfClassSchedTrn e where e.mIsValid = :name ") 
    Page<IyfClassSchedTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable);

    @Query("SELECT e from IyfClassSchedTrn e where e.mICourseConfig=:name and e.mIsValid = 1  ") //and e.mDtschedule= '02/03/2023'
	List<IyfClassSchedTrn> getClassScheduleList(@Param("name") Long id);

    @Query("SELECT e from IyfClassSchedTrn e where e.mICourseConfig=:name2 and e.mIClassId=:name ") 
	IyfClassSchedTrn getClassScheduleById(@Param("name") Long id, @Param("name2") Long course);

}
