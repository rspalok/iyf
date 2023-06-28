package net.repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.model.GbltOtpStudentRegTrn;
import net.model.master.MenuMaster; 

@Repository
@Transactional
public interface GbltOtpStudentRegTrnRepository extends JpaRepository<GbltOtpStudentRegTrn, String>{

	//List<GbltOtpStudentRegTrn> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2, String orgId);
 	List<GbltOtpStudentRegTrn> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IIsValid = :name ") 
    Page<GbltOtpStudentRegTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IIsValid = :name ") 
	List<GbltOtpStudentRegTrn> getAllValidStudent(@Param("name") Integer name);

    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber") 
	List<Object> getStudentByMobile(@Param("mobileNumber") Long mobileNumber);

    @Query("SELECT e from MenuMaster e ") 
	List<MenuMaster> getMemuList();

    //select * from iyf.gblt_otp_reg_trn as e
    //where cast(e.dt_entry as Date) = cast(getdate() as Date)

    @Query("SELECT e from GbltOtpStudentRegTrn e where stOrgId =:org and  e.IIsValid = :name and "
    		+ " month(e.dtRegistration)=:currentMonth  and year(e.dtRegistration)=:currentYear and day(e.dtRegistration) =:currentDay "
    		+ "") 
	List<GbltOtpStudentRegTrn> allCurrentRegStudent(@Param("org") String getstOrgId,@Param("name") Integer i,
			@Param("currentDay") int currentDay, @Param("currentMonth") int month,@Param("currentYear")  int currentYear);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.stStudentId=:stStudentId and e.stOrgId =:org and e.IIsValid = :isValid ") 
	List<GbltOtpStudentRegTrn> findStudentById(@Param("stStudentId") String stStudentId,@Param("org")  String orgId,Integer isValid);
}
