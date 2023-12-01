package net.transection.attendance.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

@Repository
public class OtpRegAndAttendenceDaoImpl implements OtpRegAndAttendenceDao {


	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<GbltOtpStudentRegTrn> allCurrentOtpRegStudent() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// now retrieve/read from database using name
		Query<GbltOtpStudentRegTrn> theQuery = currentSession.createQuery("from GbltOtpStudentRegTrn where IIsValid=:roleName", GbltOtpStudentRegTrn.class);
		theQuery.setParameter("roleName", 0);

		List<GbltOtpStudentRegTrn> theRole = null;

		try {
			theRole = theQuery.getResultList();
		} catch (Exception e) {
			theRole = null;
		}

		return theRole;
	}

	 
}
