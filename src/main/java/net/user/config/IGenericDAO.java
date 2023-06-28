package net.user.config;

import java.util.List;

public interface IGenericDAO {
	 	
		public <T> T save(final T o);

	    public void delete(final Object object);

	    public <T> T get(final Class<T> type, final Class<T> id);
	   
	    public <T> T merge(final T o)  ;
	   
	    public <T> void saveOrUpdate(final T o);

	    public <T> List<T> getAll(final Class<T> type);

		public <T> T deleteQuery(Class<T> class1, String jpql);

		public <T> T updateQuery(Class<T> class1, String jpql);

		public <T> T getSingleRecord(Class<T> class1, String jpql);

		public <T>List<T> getRecordList(Class<T> class1, String jpql);
}
