package net.dao.report;

import java.util.Date;
import java.util.List;

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

	/*@Query("SELECT e from IyfCoureRegTrn e JOIN e.objGbltOtpStudentRegTrns  JOIN e.ObjIYFCourseConfig  "
			+ " where e.mDtRegistration =:mDtRegistration and e.mICourseConfig=:mICourseConfig  and e.mStOrgId=:mStOrgId") 
	List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(@Param("mDtRegistration")  Date dtRegistration,@Param("mICourseConfig")  Long getmICourseConfig,
			@Param("mStOrgId") String org);
*/
}
