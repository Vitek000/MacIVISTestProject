package velosearch.model.persistence.hibernate;

import velosearch.commons.model.persistence.hibernate.DAOHibernate;
import velosearch.model.domain.AvitoMessageEntity;
import velosearch.model.persistence.AvitoMessageDAO;

/**
 * Created by Viktor Aleksandrov on 22/08/16.
 */
public class AvitoMessageDAOHibernate extends DAOHibernate implements AvitoMessageDAO {
    @Override
    protected Class getEntityClass() {
        return AvitoMessageEntity.class;
    }
}
