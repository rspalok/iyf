package net.user.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.model.master.GbltOrgMst;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltUserMst;
import net.model.master.GbltUsersRolesTrn;

@Repository
public class UserDaoImpl implements UserDao {

	// need to inject the session factory
	@Autowired
	private EntityManager entityManager;

	@Override
	public GbltUserMst findByUserName(String theUserName) {
		// get the current hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		String qu="select a from GbltUserMst a where a.stUserName=:uName ";
		
		// now retrieve/read from database using username
		Query<GbltUserMst> theQuery = currentSession.createQuery(qu, GbltUserMst.class);
										
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
	public GbltUsersRolesTrn findByUserDtlsName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		String qu=" select a.IRoleId,a.IUserId, "
				+ " a.roles as role "
				+ " from GbltUsersRolesTrn a "
				+ " where a.gbltUserMst.stUserName=:uName "
				//+ " and r.IRoleId=a.IRoleId "
				;

		System.out.println("================"+qu);
		Query<GbltUsersRolesTrn> Query = currentSession.createQuery(qu,
				GbltUsersRolesTrn.class);
		Query.setParameter("uName", theUserName);

		System.out.println("================");
		// now retrieve/read from database using username
		System.out.println(Query.getResultList());
		List<GbltUsersRolesTrn> User = null;
		try {
			User = Query.getResultList();//Query.getSingleResult();
		} catch (Exception e) {
			User = null;
		}

		return User.get(0);
	}


	@Override
	public void save(GbltUserMst theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
		System.out.println("saveOrUpdate===== "+theUser);
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
	@Override
	public GbltOrgMst allOrgDetails(String theUserName) {
		// get the current hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		String qu="select a from GbltOrgMst a where a.stOrgId=:uName ";
		
		// now retrieve/read from database using username
		Query<GbltOrgMst> theQuery = currentSession.createQuery(qu, GbltOrgMst.class);
										
		theQuery.setParameter("uName", theUserName);
		
		GbltOrgMst theOrgDetails = null;
		try {
			theOrgDetails = theQuery.getSingleResult();
		} catch (Exception e) {
			theOrgDetails = null;
		}

		return theOrgDetails;
	}
}
