/*
 * Created on 05.03.2005
 *
 */
package velosearch.commons.model.logic;
//
//import com.eastview.commons.model.help.ValueListInfo;
//import com.eastview.commons.model.query.ObjectQuery;

import java.util.Collection;
import java.util.List;

/**
 * Base interfase for domain managers
 * @author Taras
 *
 */
public interface BaseManager {
    /**
     * Get a list of all object of current type from   
     * @return list of objects
     */
    public List findAll();

    /**
     * get Total elements 
     * @return
     */
    public Long findTotalAll();


//    /**
//     * Get a list of jbjects that respods to query
//     * @param query - contains query
//     * @return
//     */
//    @Deprecated
//    public List findByQuery(ObjectQuery query);
//
//    /**
//     * get Total elements that responds to query
//     * @param objectQuery - contains query
//     * @return
//     */
//    @Deprecated
//    public Long findTotalByQuery(ObjectQuery objectQuery);
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

    

    List findByQuery(String q);


//    /**
//     * Sort and page lazy collection according to
//     * Value List info
//     * @param collection
//     * @param listInfo - contains list info
//     * @return
//     */
//    public List sortByListInfoCollection(Collection collection, ValueListInfo listInfo);

    /**
     * Get persistent object by persistent object id
     * @param objectId - persistent object id
     * @return persistent object with id = objectId
     */
    public Object findById(Integer objectId);

    /**
     * Save persistent object
     * @param obj object
     */
    public void save(Object obj);



    /**
     * Merge persistent object according to it's id
     * @param obj object
     */
    public void merge(Object obj);
    /**
     * Remove persistent object
     * @param obj object
     */
    public void delete(Object obj);
    public void deleteAll(List objs);


     /**
     * Refresh product persistent object
     * @param obj object
     */
    public void refresh(Object obj);

    /**
     * Initialize proxy object
     * @param obj object
     */
    public void initialize(Object obj);


    public void deleteById(Integer id);
}
