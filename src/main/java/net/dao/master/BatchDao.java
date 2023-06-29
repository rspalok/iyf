package net.dao.master;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFBatchMstPk;
@Transactional 
public interface BatchDao extends JpaRepository<IYFBatchMst,IYFBatchMstPk> {

    @Query("SELECT e from IYFBatchMst e where e.IsValid = :name and e.stOrgId=:stOrgId") 
    Page<IYFBatchMst> findAllByIsvalid(@Param("name") Integer name, Pageable pageable, @Param("stOrgId") String org);

    @Query("SELECT e from IYFBatchMst e where e.IsValid = 1 and e.IBatch=:IBatch and e.stOrgId=:stOrgId") 
	Optional<IYFBatchMst> findById(@Param("IBatch") Long IBatch, @Param("stOrgId") String org);

}
