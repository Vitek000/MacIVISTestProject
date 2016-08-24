package velosearch.commons.model.logic;

import velosearch.commons.model.persistence.DAO;

/**
 * BaseManagerFactory is factory interface for creation domain managers
 * <B>Date:</B> Sep 30, 2008 <BR/>
 * <B>Time:</B> 2:41:17 PM
 *
 * @author Pavel Pisarev
 */
public interface BaseManagerFactory {

    /**
     * @return BaseManager corresponds its entity
     */
    BaseManager createBaseManager(DAO dao);
}
