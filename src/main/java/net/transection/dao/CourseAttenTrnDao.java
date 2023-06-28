package net.transection.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.model.transection.IyfCourseAttenTrn;
import net.model.transection.IyfCourseAttenTrnPk;

public interface CourseAttenTrnDao extends JpaRepository<IyfCourseAttenTrn, IyfCourseAttenTrnPk>{


	@Query("SELECT s FROM IyfCourseAttenTrn s where s.mICourseConfig =:mICourseConfig and isPresent=1")//and s.IIsValid = 1
	List<IyfCourseAttenTrn> getAllPresentStudent(Long mICourseConfig);

}
