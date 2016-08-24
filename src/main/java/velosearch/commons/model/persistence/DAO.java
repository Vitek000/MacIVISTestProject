package velosearch.commons.model.persistence;

/**
 * Created by Viktor Aleksandrov on 19/08/16.
 */

import org.hibernate.Criteria;
import org.hibernate.Query;

import java.util.Collection;
import java.util.List;


/**
 * Base Data Access Object (DAO) interface
 * @author Anton Stremoukhov
 *
 */
public interface DAO {
    public List findAll();
    public List findAll(int offset, int maxResult);
    public Long findTotalAll();
    public List findByQuery(String objectQuery);

    public List findByQuery(Query q);
    public Object findById(Integer objectId);
//    public List findByIds(Collection<Integer> ids);
//    public List findByIds(Collection<Integer> ids, SortProperty sortBy);


    long findTotalByCriteria(Criteria c);


//    /**
//     * Get a list of objects that filtered according to
//     * Value List info
//     * @param listInfo - contains list info
//     * @return
//     */
//    public List findByListInfo(ValueListInfo listInfo);
//
//
//    @Deprecated
//    public List findByQuery(ObjectQuery objectQuery);
//
//
//    /**
//     * Get a list of objects that respods to query and filtered according to
//     * Value List info
//     * @param objectQuery - contains query
//     * @param listInfo - contains list info
//     * @return
//     */
//    @Deprecated
//    public List findQueriedAndListed(ObjectQuery objectQuery, ValueListInfo listInfo);
//
//    /**
//     * get Total elements that responds to query
//     * @param objectQuery - contains query
//     * @return
//     */
//    @Deprecated
//    public Long findTotalByQuery(ObjectQuery objectQuery);
//
//
//    /**
//     * Sort and page lazy collection according to
//     * Value List info
//     * @param collection
//     * @param listInfo - contains list info
//     * @return
//     */
//    public List sortByListInfoCollection(Collection collection,ValueListInfo listInfo);


    public void save(Object obj);
    public void saveAll(List objs);
    public void delete(Object obj);
    public void deleteAll(List objs);
    public void refresh(Object obj);

    /**
     * Initialize proxy object
     * @param obj object
     */
    public void initialize(Object obj);
    public void merge(Object obj);

}
