package net.dao.transection;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltOtpStudentRegTrnPk;
import net.model.master.MenuMaster;
import net.model.transection.IyfCoureRegTrn; 

@Transactional
public interface GbltOtpStudentRegTrnRepository extends JpaRepository<GbltOtpStudentRegTrn, GbltOtpStudentRegTrnPk>{

	//List<GbltOtpStudentRegTrn> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2, String orgId);
 	List<GbltOtpStudentRegTrn> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IIsValid = :name and e.stOrgId =:org ") 
    Page<GbltOtpStudentRegTrn> findAllByIsvalid(@Param("name") Integer name, Pageable pageable,@Param("org")  String stOrgId);


    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IIsValid = :name and e.stOrgId =:org") 
	List<GbltOtpStudentRegTrn> getAllValidStudent(@Param("name") Integer name ,@Param("org")  String stOrgId);

    @Query("SELECT e from GbltOtpStudentRegTrn e where e.IMobile = :mobileNumber and e.stOrgId =:org") 
	List<GbltOtpStudentRegTrn> getStudentByMobile(@Param("mobileNumber") Long mobileNumber ,@Param("org")  String stOrgId);

    @Query("SELECT e from MenuMaster e ") 
	List<MenuMaster> getMemuList();


    //1 normal only registration , 2 on course registration, 
  	//3 course attendance registration ,
  	//5 yatra registration
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


	//@Query("SELECT e from GbltOtpStudentRegTrn e where e.stStage IS NOT NULL and e.ICounselor IS NOT NULL and e.stOrgId =:org ")//e.stStage IS NOT NULL  and 
	//List<GbltOtpStudentRegTrn> getAllCallerStudent(@Param("org")  String org);
}
