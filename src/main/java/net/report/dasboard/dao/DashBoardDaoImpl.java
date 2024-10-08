package net.report.dasboard.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.model.bean.GbltStudentBean;
import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.attendance.IyfCourseAttenTrn;

@Repository
public class DashBoardDaoImpl implements DashBoardDao {
	
	@Autowired
	private EntityManager entityManeger;

	@Override
	public List<IyfCourseAttenTrn> getCourseAllCourserRegByStudentIdfromAtten(String orgId,String stStudentId) {

		Session sf = entityManeger.unwrap(Session.class);
		String Query="select s from IyfCourseAttenTrn s where s.stStudentId=:stStudentId";
		
		List<IyfCourseAttenTrn> result =sf.createQuery(Query,IyfCourseAttenTrn.class)

				.setParameter("stStudentId", stStudentId)
				.getResultList();
 
		return result;
	}

	@Override
	public List<IyfCourseAttenTrn> getCourseAllCourserRegByMobileNo(String orgId, Long mobileNumber) {
		Session sf = entityManeger.unwrap(Session.class);
		String Query="select s from IyfCourseAttenTrn s where s.objGbltOtpStudentRegTrns.IMobile=:IMobile and s.objGbltOtpStudentRegTrns.IIsValid=1";
		
		List<IyfCourseAttenTrn> result =sf.createQuery(Query,IyfCourseAttenTrn.class)

				.setParameter("IMobile", mobileNumber)
				.getResultList();
 
		return result;
	}

	@Override
	public List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(Integer date, Integer month, Integer year, Long mICourseConfig,List<Long> mICourseConfig1,
			String mStOrgId) {
		System.out.println("========"+year);
		Session sf = entityManeger.unwrap(Session.class);
		String Query="SELECT e from IyfCoureRegTrn e JOIN e.objGbltOtpStudentRegTrns  JOIN e.ObjIYFCourseConfig  "
				+ " where DAY(e.mDtRegistration) =:date "
				+ " and MONTH(e.mDtRegistration) =:month "
				+ " and YEAR(e.mDtRegistration) =:year "
				+ " and e.mICourseConfig =:mICourseConfig "
				+ " and e.stStudentId not in (select s.stStudentId from IyfCoureRegTrn s where s.mICourseConfig in (:mICourseConfig1)) "
				+ " and e.mStOrgId=:mStOrgId "
				+ " and e.objGbltOtpStudentRegTrns.IIsValid=1 ";
		
		List<IyfCoureRegTrn> result =sf.createQuery(Query,IyfCoureRegTrn.class)

				.setParameter("date", date)
				.setParameter("month", month)
				.setParameter("year", year)
				.setParameter("mICourseConfig", mICourseConfig)
				.setParameter("mICourseConfig1", mICourseConfig1)
				.setParameter("mStOrgId", mStOrgId)
				.getResultList();
 
		return result;
	}

	@Override
	public List<Map> ClassAttendance(String stStudentId, Set<Long>  mICourseConfig, String mStOrgId) {
		
		Session sf = entityManeger.unwrap(Session.class);
		
		String Query1=" select new Map( a.mIClassId as mIClassId , "
				+ " a.mIClassType as mIClassType , "
				+ " a.mStName as mStName , "
				+ " a.mDtschedule as mDtschedule , "
				+ " a.mICourseConfig as mICourseConfig ,"
				+ " b.stName as stName ,"
				+ " (select count(mIClassId) from IyfCourseAttenTrn at where "
				+ " at.mIClassId=a.mIClassId and at.mICourseConfig=a.mICourseConfig and at.stStudentId =:stStudentId) as present   " 
		+ " ) from IyfClassSchedTrn  a , IYFCourseConfig  b  where a.mICourseConfig in :mICourseConfig and a.mICourseConfig =b.mICourseConfig "
		+ " and a.mStOrgId=:mStOrgId and a. order by present desc ";

		 //where a.num_course_config_id=1 and a.num_course_config_id =b.num_course_config_id*/

		System.out.println("resultresult "+Query1);
		List<Map>  result1 =sf.createQuery(Query1,Map.class)

				.setParameter("stStudentId", stStudentId)
				.setParameter("mICourseConfig", mICourseConfig)
				.setParameter("mStOrgId", mStOrgId)
				.getResultList();
		System.out.println("resultresult "+result1);
		return result1;
	}

	@Override
	public List<Map> getAttendanceCount(Long mICourseConfig, String mStOrgId) {
		Session sf = entityManeger.unwrap(Session.class);

		String Query1=" select new Map( a.stStudentId as stStudentId , "
				+ " a.mDtRegistration as mDtRegistration , "
				+ " s.firstName as firstName , "
				+ " s.lastName as lastName, "
				+ " s.mChanting as mChanting ,"
				+ " s.stOccupation as stOccupation, "
				+ " s.stAddress as stAddress, "
				+ " s.IMobile as IMobile, "
				+ " (select count(mIClassId) from IyfCourseAttenTrn at where "
				+ "  at.stStudentId=a.stStudentId and at.mICourseConfig = a.mICourseConfig ) as present   " 
		+ " ) from IyfCoureRegTrn  a ,GbltOtpStudentRegTrn s  where a.mICourseConfig = :mICourseConfig "
		+ " and a.stStudentId =s.stStudentId "
		+ " and a.mStOrgId=:mStOrgId and s.IIsValid=1 order by present desc";


		System.out.println("resultresult "+Query1);
		List<Map>  result1 =sf.createQuery(Query1,Map.class)

				.setParameter("mICourseConfig", mICourseConfig)
				.setParameter("mStOrgId", mStOrgId)
				.getResultList();
		System.out.println("resultresult "+result1);
		
		return result1;
	}

	@Override
	public List<Map> getAttendanceCount(String org,GbltStudentBean gbltStudentBean) {
		Session sf = entityManeger.unwrap(Session.class);
		gbltStudentBean.setmICourseConfig((long) 5);
		String Query1=" select new Map( a.stStudentId as stStudentId , "
				+ " a.mDtRegistration as mDtRegistration , "
				+ " s.firstName as firstName , "
				+ " s.lastName as lastName, "
				+ " s.mChanting as mChanting ,"
				+ " s.stOccupation as stOccupation, "
				+ " s.stAddress as stAddress, "
				+ " s.IMobile as IMobile, "
				+ " (select count(mIClassId) from IyfCourseAttenTrn at where "
				+ "  at.stStudentId=a.stStudentId and at.mICourseConfig = a.mICourseConfig ) as present " 
		+ " ) from IyfCoureRegTrn  a ,GbltOtpStudentRegTrn s  where a.mICourseConfig = :mICourseConfig "
		+ " and a.stStudentId =s.stStudentId and  a.stStudentId not in (select w.stStudentId from "
		+ " IyfCoureRegTrn as w where w.mICourseConfig !=a.mICourseConfig )"
		+ " and a.mStOrgId=:mStOrgId and s.IIsValid=1 order by present desc";


		System.out.println("resultresult "+Query1);
		List<Map>  result1 =sf.createQuery(Query1,Map.class)

				.setParameter("mICourseConfig", gbltStudentBean.getmICourseConfig())
				.setParameter("mStOrgId", org)
				.getResultList();
		System.out.println("resultresult "+result1);
		
		return result1;
	}

}
