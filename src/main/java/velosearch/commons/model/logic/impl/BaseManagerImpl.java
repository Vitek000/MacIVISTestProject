/*
 * Created on 05.03.2005
 *
 */
package velosearch.commons.model.logic.impl;

import org.springframework.transaction.annotation.Transactional;
import velosearch.commons.model.logic.BaseManager;
import velosearch.commons.model.persistence.DAO;

import java.util.List;

/**
 * Base Manager Implementation
 * @author Murashko Taras
 *
 */
@Transactional(value = "avitodb_TransactionManager", readOnly = true)
public abstract class BaseManagerImpl implements BaseManager {

	/* (non-Javadoc)
	 * @see com.eastview.commons.model.logic.BaseManager#findAll()
	 */
	public List findAll() {
		return getDAO().findAll();
	}

	/* (non-Javadoc)
	 * @see com.eastview.commons.model.logic.BaseManager#findTotalAll()
	 */
	public Long findTotalAll() {
		return getDAO().findTotalAll();
	}


//
//	/* (non-Javadoc)
//	 * @see com.eastview.commons.model.logic.BaseManager#findByQuery(com.eastview.commons.model.query.ObjectQuery)
//	 */
//	@Deprecated
//	public List findByQuery(ObjectQuery query) {
//		return getDAO().findByQuery(query);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.eastview.commons.model.logic.BaseManager#findTotalByQuery(com.eastview.commons.model.query.ObjectQuery)
//	 */
//	@Deprecated
//	public Long findTotalByQuery(ObjectQuery objectQuery) {
//		return getDAO().findTotalByQuery(objectQuery);
//	}
//
//
//	/* (non-Javadoc)
//	 * @see com.eastview.commons.model.logic.BaseManager#findByListInfo(com.eastview.commons.model.help.ValueListInfo)
//	 */
//	public List findByListInfo(ValueListInfo listInfo) {
//		return getDAO().findByListInfo(listInfo);
//	}
//
//
//	/* (non-Javadoc)
//	 * @see com.eastview.commons.model.logic.BaseManager#findQueriedAndListed(com.eastview.commons.model.query.ObjectQuery, com.eastview.commons.model.help.ValueListInfo)
//	 */
//	@Deprecated
//	public List findQueriedAndListed(ObjectQuery objectQuery,
//									 ValueListInfo listInfo) {
//		return getDAO().findQueriedAndListed(objectQuery, listInfo);
//	}


	public List findByQuery(String objectQuery) {
		return getDAO().findByQuery(objectQuery);
	}
	
//
//	/* (non-Javadoc)
//         * @see com.eastview.commons.model.logic.BaseManager#sortByListInfoCollection(java.util.Collection, com.eastview.commons.model.help.ValueListInfo)
//         */
//	public List sortByListInfoCollection(Collection collection,
//			ValueListInfo listInfo) {
//		return getDAO().sortByListInfoCollection(collection, listInfo);
//	}
//
    
	/* (non-Javadoc)
	 * @see com.eastview.commons.model.logic.BaseManager#findById(java.lang.Integer)
	 */
	public Object findById(Integer objectId) {
		return getDAO().findById(objectId);
	}


	@Transactional(value = "avitodb_TransactionManager")
	public void save(Object obj) {
		getDAO().save(obj);
	}


	@Transactional(value = "avitodb_TransactionManager")
    public void merge(Object obj) {
        getDAO().merge(obj);
    }


	@Transactional(value = "avitodb_TransactionManager")
	public void delete(Object obj) {
		getDAO().delete(obj);        
	}


	@Transactional(value = "avitodb_TransactionManager")
	public void deleteAll(List objs) {
        for (Object obj : objs) {
            delete(obj);
        }
    }

	@Transactional(value = "avitodb_TransactionManager")
	public void deleteById(Integer id) {
        getDAO().delete(findById(id));
	}


	@Transactional(value = "avitodb_TransactionManager")
	public void refresh(Object obj) {
		getDAO().refresh(obj);

	}

    public void initialize(Object obj) {
		getDAO().initialize(obj);

	}
    

	abstract protected DAO getDAO();
}
