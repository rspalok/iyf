package net.master.org.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.pojo.org.GbltOrgUnitMst;
import net.model.master.pojo.org.GbltOrgUnitMstPk;
@Transactional 
public interface OrgDao extends JpaRepository<GbltOrgUnitMst,GbltOrgUnitMstPk> {

    @Query("SELECT e from GbltOrgUnitMst e where e.IIsValid = :name and e.stOrgId=:stOrgId") 
    Page<GbltOrgUnitMst> findAllByIsvalid(@Param("name") Integer name, Pageable pageable, @Param("stOrgId") String org);

    @Query("SELECT e from GbltOrgUnitMst e where e.IIsValid = 1 and e.mOrgUnit=:mOrgUnit and e.stOrgId=:stOrgId") 
	Optional<GbltOrgUnitMst> findById(@Param("mOrgUnit") Integer IBatch, @Param("stOrgId") String org);

}
