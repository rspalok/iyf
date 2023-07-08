package net.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;

import net.model.master.IyfCourseMst;

public interface CourseDao extends JpaRepository<IyfCourseMst,Long>{

}
