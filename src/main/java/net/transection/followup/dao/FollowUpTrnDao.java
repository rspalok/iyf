package net.transection.followup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.transection.pojo.followup.FollowUpTrn;
import net.model.transection.pojo.followup.FollowUpTrnPk;

public interface FollowUpTrnDao extends JpaRepository<FollowUpTrn, FollowUpTrnPk>{

	@Query("SELECT e from FollowUpTrn e JOIN e.objFollowUpMaster  where  e.isValid = :name and e.stOrgId =:org") 
    Page<FollowUpTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org")  String org);

}
