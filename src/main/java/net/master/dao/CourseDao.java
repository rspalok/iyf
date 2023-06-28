package net.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.model.master.pojo.IyfCourseMst;

public interface CourseDao extends JpaRepository<IyfCourseMst,Long>{

}
