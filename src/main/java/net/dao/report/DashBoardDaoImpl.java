package net.dao.report;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCourseAttenTrn;

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
		String Query="select s from IyfCourseAttenTrn s where s.objGbltOtpStudentRegTrns.IMobile=:IMobile";
		
		List<IyfCourseAttenTrn> result =sf.createQuery(Query,IyfCourseAttenTrn.class)

				.setParameter("IMobile", mobileNumber)
				.getResultList();
 
		return result;
	}

	@Override
	public List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(Date mDtRegistration, Long mICourseConfig,
			String mStOrgId) {
		Session sf = entityManeger.unwrap(Session.class);
		String Query="SELECT e from IyfCoureRegTrn e JOIN e.objGbltOtpStudentRegTrns  JOIN e.ObjIYFCourseConfig  "
				+ " where e.mDtRegistration =:mDtRegistration and e.mICourseConfig=:mICourseConfig  and e.mStOrgId=:mStOrgId";
		
		List<IyfCoureRegTrn> result =sf.createQuery(Query,IyfCoureRegTrn.class)

				.setParameter("mDtRegistration", mDtRegistration)
				.setParameter("mICourseConfig", mICourseConfig)
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
		+ " and a.mStOrgId=:mStOrgId";

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
				+ "  at.stStudentId=a.stStudentId ) as present   " 
		+ " ) from IyfCoureRegTrn  a ,GbltOtpStudentRegTrn s  where a.mICourseConfig = :mICourseConfig "
		+ " and a.stStudentId =s.stStudentId "
		+ " and a.mStOrgId=:mStOrgId";

		 //where a.num_course_config_id=1 and a.num_course_config_id =b.num_course_config_id*/

		System.out.println("resultresult "+Query1);
		List<Map>  result1 =sf.createQuery(Query1,Map.class)

				.setParameter("mICourseConfig", mICourseConfig)
				.setParameter("mStOrgId", mStOrgId)
				.getResultList();
		System.out.println("resultresult "+result1);
		
		
 
		return result1;
	}

	/*@Query("SELECT e from IyfCoureRegTrn e JOIN e.objGbltOtpStudentRegTrns  JOIN e.ObjIYFCourseConfig  "
			+ " where e.mDtRegistration =:mDtRegistration and e.mICourseConfig=:mICourseConfig  and e.mStOrgId=:mStOrgId") 
	List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(@Param("mDtRegistration")  Date dtRegistration,@Param("mICourseConfig")  Long getmICourseConfig,
			@Param("mStOrgId") String org);
*/
}
