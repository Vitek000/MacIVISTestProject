package velosearch.commons.model.persistence;

/**
 * DAOFactory is factory interface for creation DAO
 * <B>Date:</B> Sep 30, 2008 <BR/>
 * <B>Time:</B> 2:35:07 PM
 *
 * @author Pavel Pisarev
 */
public interface DAOFactory {

    /**
     * @return DAO corresponds its entity
     */
    public DAO createDao(final String className) throws ClassNotFoundException;
}
