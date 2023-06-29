package net.dao.report;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
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
	 
	 

}
