package net.master.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.model.master.pojo.course.IyfCourseMst;

public interface CourseDao extends JpaRepository<IyfCourseMst,Long>{

}
