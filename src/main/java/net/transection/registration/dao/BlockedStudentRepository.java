package net.transection.registration.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.model.transection.pojo.BlockedStudent;
import net.model.transection.pojo.BlockedStudentPk;

@Transactional
public interface BlockedStudentRepository extends JpaRepository<BlockedStudent, BlockedStudentPk>{

}
