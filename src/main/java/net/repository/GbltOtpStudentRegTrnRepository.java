package net.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.model.GbltOtpStudentRegTrn;

@Repository
public interface GbltOtpStudentRegTrnRepository extends JpaRepository<GbltOtpStudentRegTrn, String>{

	List<GbltOtpStudentRegTrn> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2);

	//Optional<GbltOtpStudentRegTrn> findById(String id);

	//List<GbltOtpStudentRegTrn> findAllOrderByDateAsc();

}
