package velosearch.model.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velosearch.commons.model.logic.BaseManager;
import velosearch.commons.model.logic.impl.BaseManagerImpl;
import velosearch.commons.model.persistence.DAO;
import velosearch.model.logic.AvitoMessageManager;
import velosearch.model.persistence.AvitoMessageDAO;

import java.util.Collection;
import java.util.List;

/**
 * Created by Viktor Aleksandrov on 22/08/16.
 */
@Service("avitoMessageManager")
@Transactional(value = "avitodb_TransactionManager", readOnly = true)
public class AvitoMessageManagerImpl extends BaseManagerImpl implements AvitoMessageManager {

    @Autowired
    private AvitoMessageDAO avitoMessageDAO;

    @Override
    protected DAO getDAO() {
        return avitoMessageDAO;
    }


}
