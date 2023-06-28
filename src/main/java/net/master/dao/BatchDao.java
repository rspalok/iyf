package net.master.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFCourseConfig;
@Transactional
@Repository
public interface BatchDao extends JpaRepository<IYFBatchMst,Long> {
 
	

    @Query("SELECT e from IYFBatchMst e where e.IsValid = :name ") 
    Page<IYFBatchMst> findAllByIsvalid(@Param("name") Integer name, Pageable pageable);

}
