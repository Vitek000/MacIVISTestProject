package velosearch.model.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import velosearch.model.AvitoDBModelTestPersistenceConfig;
import velosearch.model.logic.impl.AvitoMessageManagerImpl;

/**
 * Created by Viktor Aleksandrov on 22/08/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AvitoDBModelTestPersistenceConfig.class)
public class AvitoMessageManagerTest {

    @Autowired
    private AvitoMessageManager avitoMessageManager;


//    @Test
//    public void testGetSessionById() {
//        userSessionManager.getSessionById("abc");
//    }
}
