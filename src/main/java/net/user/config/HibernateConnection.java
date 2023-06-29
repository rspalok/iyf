package net.user.config;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateConnection {
	 
	 
		@Autowired 
		EntityManager sf;
		
		public String generateTransSringTestPk(SharedSessionContractImplementor session, String pkName,
				Map<String, Object> params) throws HibernateException {

			String id = "";

			try {

				session.getNamedQuery("selectTransPk")
						.setParameter("pkName", pkName)
						.setLockOptions(LockOptions.UPGRADE).uniqueResult();

				Query queryObj = session.getNamedQuery(pkName);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				id = (String) queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}

			session.getNamedQuery("updateTransPk").setParameter("pkValue", String.valueOf(id))
					.setParameter("pkName", pkName).executeUpdate();

			System.out.println("String ID is generated with :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+id);
			return id;
		}
		
		
		
		public List<?> executeHqlQuery(String hqlQuery,
				Map<String, Object> params) throws Exception {

			if (hqlQuery == null
					|| (hqlQuery != null && hqlQuery.trim().length() == 0))
				throw new Exception("Query value is null or empty");

			List<?> list = null;
			
			Session s = ((SessionFactory) sf).getCurrentSession();	

			try {
				Query queryObj = s.createQuery(hqlQuery);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						System.out.println(entry.getKey() + "  -> "+entry.getValue());

						queryObj.setParameter(entry.getKey(), entry.getValue());
						
					}

				list = queryObj.list();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				s.close();
				s = null;
			}
			return list;
		}

		public  Object executeHqlQuerySingleResult(String hqlQuery,
				Map<String, Object> params) throws Exception {

			if (hqlQuery == null
					|| (hqlQuery != null && hqlQuery.trim().length() == 0))
				throw new Exception("Query value is null or empty");

			Object list = null;
			Session s = ((SessionFactory) sf).getCurrentSession();

			try {
				Query queryObj = s.createQuery(hqlQuery);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				list = queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				s.close();
				s = null;
			}
			return list;
		}

		
		public  Object executeSqlQuerySingleResult(String hqlQuery,
				Map<String, Object> params) throws Exception {

			if (hqlQuery == null
					|| (hqlQuery != null && hqlQuery.trim().length() == 0))
				throw new Exception("Query value is null or empty");

			Object list = null;
			
			Session s = ((SessionFactory) sf).getCurrentSession();

			try {
				Query queryObj = s.createSQLQuery(hqlQuery);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				list = queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				s.close();
				s = null;
			}
			return list;
		} 
		
		 
		public Integer generatePk(SharedSessionContractImplementor session, String pkName,
				Map<String, Object> params) throws HibernateException {

			Integer id = 0;

			try {

				session.getNamedQuery("selectPk").setParameter("pkName", pkName)
						.setLockOptions(LockOptions.UPGRADE).uniqueResult();

				Query queryObj = session.getNamedQuery(pkName);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						System.out.println(entry.getKey() +" -> "+entry.getValue());
						
						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				id = (Integer) queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}

			session.getNamedQuery("updatePk").setParameter("pkValue", String.valueOf(id))
					.setParameter("pkName", pkName).executeUpdate();

			return id+1;
		}

		public Long generatePkLong(SharedSessionContractImplementor session, String pkName,
				Map<String, Object> params) throws HibernateException {

			Long id = 0L;

			try {
				session.getNamedQuery("selectPk").setParameter("pkName", pkName)
				.setLockOptions(LockOptions.UPGRADE).uniqueResult(); 
				
				Query queryObj = session.getNamedQuery(pkName);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				id = (Long) queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}

			session.getNamedQuery("updateTransPk").setParameter("pkValue", String.valueOf(id))
					.setParameter("pkName", pkName).executeUpdate();

			System.out.println("ID is generated with :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+id);

			return id;
		}

		public Integer generateTransPk(SharedSessionContractImplementor session, String pkName,
				Map<String, Object> params) throws HibernateException {

			Integer id = 0;
			System.out.println("======  "+pkName);
			System.out.println("params   "+params);
			System.out.println();

			try {

				session.getNamedQuery("selectPk").setParameter("pkName", pkName)
				.setLockOptions(LockOptions.UPGRADE).uniqueResult();
				
				Query queryObj = session.getNamedQuery(pkName);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				id = (Integer) queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}

			session.getNamedQuery("updateTransPk").setParameter("pkValue", String.valueOf(id))
					.setParameter("pkName", pkName).executeUpdate();

			System.out.println("ID is generated with :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+id);
			return id;
		}

		public Long generateTransPk(SharedSessionContractImplementor session, String query,
				String pkName, Map<String, Object> params)
				throws HibernateException {

			Long id = 0L;

			try {

				session.getNamedQuery("selectTransPk")
						.setParameter("pkName", pkName)
						.setLockOptions(LockOptions.UPGRADE).uniqueResult();

				Query queryObj = session.getNamedQuery(query);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				id = (Long) queryObj.uniqueResult();

			} catch (Exception e) {
				System.err.println(e);
			}

			session.getNamedQuery("updateTransPk").setParameter("pkValue", id)
					.setParameter("pkName", pkName).executeUpdate();

			return id;
		}
		public String generateTransSringPk(SharedSessionContractImplementor session, String pkName,
				Map<String, Object> params) throws HibernateException {

			String id = "";

			try {

				session.getNamedQuery("selectTransPk")
						.setParameter("pkName", pkName)
						.setLockOptions(LockOptions.UPGRADE).uniqueResult();

				Query queryObj = session.getNamedQuery(pkName);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}

				id = (String) queryObj.uniqueResult();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}

			session.getNamedQuery("updateTransPk").setParameter("pkValue", String.valueOf(id))
					.setParameter("pkName", pkName).executeUpdate();

			System.out.println("String ID is generated with :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+id);
			return id;
		}
		
		
		
		public Long generateTransLongPk(SharedSessionContractImplementor session, String pkName,
				Map<String, Object> params) throws HibernateException {

			Long id = (long) 0;

			try {

				session.getNamedQuery("selectTransPk")
						.setParameter("pkName", pkName)
						.setLockOptions(LockOptions.UPGRADE).uniqueResult();

				Query queryObj = session.getNamedQuery(pkName);

				if (params != null)
					for (Map.Entry<String, Object> entry : params.entrySet()) {

						queryObj.setParameter(entry.getKey(), entry.getValue());
					}
				
				id = (Long) queryObj.uniqueResult();
				System.out.println("ID is generated with 2 ::::"+id);

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}

			session.getNamedQuery("updateTransPk").setParameter("pkValue", String.valueOf(id))
					.setParameter("pkName", pkName).executeUpdate();

			System.out.println("ID is generated with ::::"+id);
			return id;
		} 
	}
