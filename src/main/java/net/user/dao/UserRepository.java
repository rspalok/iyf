package net.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.model.GbltUserMst;
import net.model.GbltUserMstPk;


public interface UserRepository extends CrudRepository<GbltUserMst, GbltUserMstPk> {

	@Query("SELECT u FROM GbltUserMst u WHERE u.stUserName = :username")
	public GbltUserMst getUserByUsername(@Param("username") String username);

	@Query("SELECT u FROM GbltUserMst u ")
	public List<GbltUserMst> getAllCallerStudent(String org);


}
