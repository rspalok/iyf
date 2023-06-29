package net.user.dao;



import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDAO implements IGenericDAO{

	@Autowired
    private EntityManager sf;

    @SuppressWarnings("unchecked")
	public <T> T save(final T o){
      return (T) ((SessionFactory) sf).getCurrentSession().save(o);
    }


    public void delete(final Object object){
      ((SessionFactory) sf).getCurrentSession().delete(object);
    }

    /***/
    public <T> T get(final Class<T> type, final Class<T>  id){
      return (T) ((SessionFactory) sf).getCurrentSession().get(type, id);
    }

    /***/
    public <T> T merge(final T o)   {
      return (T) ((SessionFactory) sf).getCurrentSession().merge(o);
    }

    /***/
    public <T> void saveOrUpdate(final T o){
      ((SessionFactory) sf).getCurrentSession().saveOrUpdate(o);
    }

    public <T> List<T> getAll(final Class<T> type) {
    	
    	//return ((SessionFactory) sf).getCurrentSession().get).
      final Session  session = ((SessionFactory) sf).getCurrentSession();
      final Criteria crit = session.createCriteria(type);
      crit.add(Restrictions.ne("gnumIsvalid", 0));
      
      crit.addOrder(Order.asc("id.numStuQuotaId"));
      return crit.list();
    }
    
    public <T> List<T> getThroughQuery(final Class<T> type, String query_p) {
    	
    	//return ((SessionFactory) sf).getCurrentSession().get).
      final Session  session = ((SessionFactory) sf).getCurrentSession();
      final Criteria crit = session.createCriteria(type);
      //System.out.println(">>>>>>>>>>>>>>>>>>>>generics>>>>>>>>>>>>>>>>>"+crit.list());
      return crit.list();
    }


	@Override
	public <T> T deleteQuery(Class<T> class1, String jpql) {
		final Session  session = ((SessionFactory) sf).getCurrentSession();
        Query qry = session.createQuery(jpql);
        int res = qry.executeUpdate();
		return null;
	}


	@Override
	public <T> T updateQuery(Class<T> class1, String jpql) {
		final Session  session = ((SessionFactory) sf).getCurrentSession();
        Query qry = session.createQuery(jpql);
        int res = qry.executeUpdate();
		return null;
	}

	@Override
	public <T> T getSingleRecord(Class<T> class1,	String jpql) {
		
		return ((SessionFactory) sf).getCurrentSession().createQuery(jpql,class1).getSingleResult();
	}

	@Override
	public <T> List<T> getRecordList(Class<T> class1, String jpql) {
		// TODO Auto-generated method stub
		return ((SessionFactory) sf).getCurrentSession().createQuery(jpql, class1).getResultList();
	}


	

}