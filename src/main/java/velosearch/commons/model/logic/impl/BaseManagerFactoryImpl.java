/**
 * Copyright 2008 "East View Information Services, Inc"  
 */
package velosearch.commons.model.logic.impl;


import velosearch.commons.model.logic.BaseManager;
import velosearch.commons.model.logic.BaseManagerFactory;
import velosearch.commons.model.persistence.DAO;

/**
 * BaseManagerFactory is implementation of BaseManagerFactory based on the DAO concept
 * TODO: Create dao using entity class
 * <B>Date:</B> Sep 30, 2008 <BR/>
 * <B>Time:</B> 2:45:12 PM
 *
 * @author Pavel Pisarev
 */
public class BaseManagerFactoryImpl implements BaseManagerFactory {
    
    public BaseManager createBaseManager(final DAO dao) {
        return new BaseManagerImpl() {
            protected DAO entityDAO = dao;

            public DAO getDAO() {
                return entityDAO;
            }
        };
    }
}
