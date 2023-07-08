package net.user.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.model.master.GbltRolMst;
import net.model.master.GbltUserMst;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public GbltRolMst findRoleByName(Integer theRoleName) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using name
		Query<GbltRolMst> theQuery = currentSession.createQuery("from GbltRolMst where IRoleId=:roleName", GbltRolMst.class);
		theQuery.setParameter("roleName", theRoleName);

		GbltRolMst theRole = null;

		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}

		return theRole;
	}
}
