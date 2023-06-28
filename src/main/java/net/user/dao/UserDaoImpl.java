package net.user.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.GbltUsersRolesTrn;

@Repository
public class UserDaoImpl implements UserDao {

	// need to inject the session factory
	@Autowired
	private EntityManager entityManager;

	@Override
	public GbltUserMst findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<GbltUserMst> theQuery = currentSession.createQuery("select a from GbltUserMst a " 
				+ " where a.stUserName=:uName ",
				GbltUserMst.class);
		theQuery.setParameter("uName", theUserName);
		GbltUserMst theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(GbltUserMst theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public GbltOtpStudentRegTrn findByregistrationId(String registrationId, String stOrgId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<GbltOtpStudentRegTrn> theQuery = currentSession.createQuery("from GbltOtpStudentRegTrn where stStudentId=:uName and stOrgId=:orgId",
				GbltOtpStudentRegTrn.class);
		theQuery.setParameter("uName", registrationId);
		theQuery.setParameter("orgId", stOrgId);
		GbltOtpStudentRegTrn theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void saveTrn(GbltUsersRolesTrn userTrn) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(userTrn);
	}
}
