/*
 * Created on 28.02.2005
 *
 */
package velosearch.commons.model.persistence.hibernate;


import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.RowCountProjection;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import velosearch.commons.model.persistence.DAO;

import java.util.List;

/**
 * Hibernate implementation of DAO
 * @author Anton Stremoukhov
 *
 */
public abstract class DAOHibernate implements DAO {
    private static Logger log = Logger.getLogger(DAOHibernate.class);
    protected SessionFactory sessionFactory;


    protected Session getSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch(HibernateException ignore) {}

        if (session == null) {
            session = sessionFactory.openSession();
            TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
            log.debug("Returning new Hibernate Session @" + Integer.toHexString(session.hashCode()));
        }

        return session;
    }


    abstract protected Class getEntityClass();


    public List findAll() {
        Criteria c = getSession().createCriteria(getEntityClass()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return c.list();
    }


    protected Object findOneByCriteria(Criteria crit) {
        return crit.setMaxResults(1).uniqueResult();
    }


    public List findAll(int offset, int maxResult) {
        //DetachedCriteria c = DetachedCriteria.forClass(getEntityClass()); //getSession().createCriteria(getEntityClass());
        Criteria c = getSession().createCriteria(getEntityClass());
        c.addOrder(Order.asc("id"));
        c.setFirstResult(offset);
        c.setMaxResults(maxResult);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return c.list();
    }


    public Long findTotalAll() {
        Criteria c = getSession().createCriteria(getEntityClass()).setProjection(new RowCountProjection());
        return (Long)c.list().get(0);
    }


    public List findByQuery(final String objectQuery) {
        Query q = getSession().createQuery(objectQuery);
        return findByQuery(q);
    }


    public List findByQuery(final Query q) {
        return q.list();
    }


//    @Deprecated
//    public List findByQuery(final ObjectQuery objectQuery) {
//        Query query = getSession().createQuery(objectQuery.assembleQuery(getEntityClass().getName()));
//        setParametersToQuery(query, objectQuery);
//        return query.list();
//    }


    public Object findById(Integer objectId) {
        return getSession().get(getEntityClass(), objectId);
    }

//    public List findByIds(Collection ids){
//        return findByIds(ids, null);
//    }


//    /**
//     * If sortBy = null, sorting is not applied.
//     * @param ids
//     * @param sortBy
//     * @return
//     */
//    public List findByIds(Collection<Integer> ids, SortProperty sortBy) {
//        List result = new ArrayList();
//
//        Session session = getSession();
//
//        Criteria criteria = session.createCriteria(getEntityClass());
//
//        if (ids != null && ids.size() > 0) {
//            criteria.add(Restrictions.in("id", ids));
//
//            if (sortBy != null) {
//                SortType type = sortBy.getType();
//
//                switch (type){
//                    case ASC:
//                        criteria.addOrder(Order.asc(sortBy.getName()));
//                        break;
//                    case DESC:
//                        criteria.addOrder(Order.desc(sortBy.getName()));
//                        break;
//
//                    default: break;
//                }
//            }
//
//            result = criteria.list();
//        }
//
//
//
//        return result;
//
//    }


    public long findTotalByCriteria(Criteria c) {
        return (Long) c.setProjection(Projections.rowCount()).uniqueResult();
    }

    
//    @Deprecated
//    public Long findTotalByQuery(final ObjectQuery objectQuery) {
//        Query query = getSession().createQuery(objectQuery.assembleTotalCountQuery(getEntityClass().getName()));
//        setParametersToQuery(query, objectQuery);
//        return (Long)query.list().get(0);
//    }
//
//
//    @Deprecated
//    public List findQueriedAndListed(final ObjectQuery objectQuery, final ValueListInfo listInfo) {
//        Query query = createQuery(getSession(), objectQuery, listInfo);
//        return query.list();
//    }
//
//
//    /**
//     * Bind parameters from obect query to hibernate query
//     * @param query hibernate query
//     * @param objectQuery object query
//     */
//    @Deprecated
//    private void setParametersToQuery(Query query, ObjectQuery objectQuery) {
//        for (Map.Entry<String, Object> stringObjectEntry : objectQuery.getParameters().entrySet()) {
//            Map.Entry element = (Map.Entry) stringObjectEntry;
//            query.setParameter((String) element.getKey(), element.getValue());
//        }
//    }
//
//    public List findByListInfo(final ValueListInfo listInfo) {
//        Query query = createQuery(getSession(), new ObjectQuery(), listInfo);
//        return query.list();
//    }
//
//    /**
//     * Create Hibernate Query from ObjectQuery and ValueListInfo
//     * @param session Hibernate session
//     * @param objectQuery query to assemble
//     * @param listInfo value list info
//     * @return Hibernate Query
//     */
//    @Deprecated
//    private Query createQuery(Session session, ObjectQuery objectQuery, ValueListInfo listInfo) {
//        Query query = session.createQuery(objectQuery.assembleQuery(getEntityClass().getName(), listInfo));
//        setParametersToQuery(query, objectQuery);
//
//        if (listInfo != null && listInfo.getPagingPage() > 0) {
//            int firstRow = listInfo.getPagingNumberPer() * (listInfo.getPagingPage() - 1);
//            query.setFirstResult(firstRow);
//            query.setMaxResults(listInfo.getPagingNumberPer());
//        }
//        return query;
//    }
//
//
//    public List sortByListInfoCollection(final Collection collection, final ValueListInfo listInfo) {
//        //String direction = (ValueListInfo.ASCENDING_DIRECTION.equals(listInfo.getSortingDirection())) ? "asc" : "desc";
//        Query query = getSession().createFilter(collection,
//                " order by :orderBy " + listInfo.getSortingDirection().getValue());
//        query.setParameter("orderBy",listInfo.getSortingColumn());
//        query.setFirstResult(listInfo.getPagingNumberPer()*(listInfo.getPagingPage()-1));
//        query.setMaxResults(listInfo.getPagingNumberPer());
//        return query.list();
//    }


    public void save(Object obj) {
        getSession().saveOrUpdate(obj);
    }

    public void saveAll(List objs) {
        for (Object obj : objs) {
            save(obj);
        }
    }

    public void deleteAll(List objs) {
        for (Object obj : objs) {
            delete(obj);
        }
    }

    public void delete(Object obj) {
        getSession().delete(obj);

    }

    public void refresh(Object obj) {
        getSession().refresh(obj);
    }

    public void initialize(Object obj) {
        Hibernate.initialize(obj);
    }

    public void merge(Object obj) {
        getSession().merge(obj);
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
