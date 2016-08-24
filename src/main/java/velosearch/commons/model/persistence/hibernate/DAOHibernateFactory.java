/**
 * Copyright 2008 "East View Information Services, Inc"  
 */
package velosearch.commons.model.persistence.hibernate;
import org.hibernate.SessionFactory;
import velosearch.commons.model.persistence.DAOFactory;
import velosearch.commons.model.persistence.DAO;

/**
 * DAOHibernateFactory implements DAOFactory based on hibernate realization of DAO
 * <B>Date:</B> Sep 30, 2008 <BR/>
 * <B>Time:</B> 12:27:40 PM
 *
 * @author Anton Stremoukhov
 */
public class DAOHibernateFactory implements DAOFactory {
    private SessionFactory sessionFactory;
    
    
    public DAO createDao(final String className) throws ClassNotFoundException {
        final Class eClass = Class.forName(className);
        
        DAOHibernate dao = new DAOHibernate() {
            protected Class entityClass = eClass;
            
            public Class getEntityClass() {
                return entityClass;
            }
        };
        
        dao.setSessionFactory(sessionFactory);
        return dao;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
