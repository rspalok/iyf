package net.transection.registration.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.pojo.menu.MenuMaster;
import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrnPk; 

@Transactional
public interface GbltOtpStudentRegTrnRepository extends JpaRepository<GbltOtpStudentRegTrn, GbltOtpStudentRegTrnPk>{

	List<GbltOtpStudentRegTrn> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IIsValid = :name and e.stOrgId =:org ") 
    Page<GbltOtpStudentRegTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org")  String stOrgId);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IIsValid = :name and e.stOrgId =:org") 
	List<GbltOtpStudentRegTrn> getAllValidStudent(@Param("name") Integer name ,@Param("org")  String stOrgId);

    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber and e.stOrgId =:org") 
	List<GbltOtpStudentRegTrn> getStudentByMobile(@Param("mobileNumber") Long mobileNumber ,@Param("org")  String stOrgId);

    @Query("SELECT e from MenuMaster e ") 
	List<MenuMaster> getMemuList();

    @Query("SELECT e from GbltOtpStudentRegTrn e where stOrgId =:org and  e.IIsValid = :name and e.mRegMode=:mRegMode and "
    		+ " month(e.dtRegistration)=:currentMonth  and year(e.dtRegistration)=:currentYear and day(e.dtRegistration) =:currentDay "
    		+ "") 
	List<GbltOtpStudentRegTrn> allCurrentRegStudent(@Param("org") String getstOrgId,@Param("name") Integer i,
			@Param("currentDay") int currentDay, @Param("currentMonth") int month,@Param("currentYear")  int currentYear,@Param("mRegMode")  int j);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.stStudentId=:stStudentId and e.stOrgId =:org and e.IIsValid = :isValid ") 
	List<GbltOtpStudentRegTrn> findStudentById(@Param("stStudentId") String stStudentId,@Param("org")  String orgId,Integer isValid);

    @Query("SELECT e from GbltOtpStudentRegTrn e where e.stStudentId=:stStudentId and e.stOrgId =:org ") 
	GbltOtpStudentRegTrn getStudentByIds(@Param("stStudentId") String stStudentId,@Param("org")  String stOrgId);


    @Query("SELECT e from GbltOtpStudentRegTrn e where stOrgId =:org and  e.IIsValid = 1 and e.mRegMode=:mRegMode and "
    		+ " e.dtRegistration BETWEEN :dtFrom AND :dtTo "
    		+ " ") 
    List<GbltOtpStudentRegTrn> getRegisterdStudentBetweenDates(@Param("dtFrom") Date date,@Param("dtTo") Date date1,@Param("mRegMode") Integer regMode,@Param("org")  String org);

    @Query("SELECT e from IyfCoureRegTrn e JOIN e.objGbltOtpStudentRegTrns  JOIN e.ObjIYFCourseConfig  "
	+ " where e.mDtRegistration BETWEEN :dtFrom AND :dtTo  and e.mStOrgId=:mStOrgId")
	List<IyfCoureRegTrn> getCourseRegisterdStudentBetweenDates(@Param("dtFrom") Date date,@Param("dtTo") Date date1,@Param("mStOrgId")  String org);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.stStudentId = :stStudentId and e.stOrgId =:org") 
   	List<GbltOtpStudentRegTrn> getStudentByStudentId(@Param("stStudentId") String stStudentId ,@Param("org")  String stOrgId);


}
