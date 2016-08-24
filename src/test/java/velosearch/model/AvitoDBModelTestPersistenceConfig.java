/**
 * Copyright 2016 "East View Information Services, Inc"
 */
package velosearch.model;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import velosearch.commons.model.AvitoDBPersistenceConfig;

import javax.annotation.PostConstruct;

/**
 * UdbModelPersistenceConfig
 * <B>Date:</B> 04/08/16 <BR/>
 * <B>Time:</B> 15:58
 *
 * @author Anton Stremoukhov
 */
@Configuration             
@Import({AvitoDBPersistenceConfig.class }) //, UdbAppPersistenceConfig.class, UdbPersistenceConfig.class }) //, UdbStatPersistenceConfig.class })
public class AvitoDBModelTestPersistenceConfig {
    private static Logger logger = Logger.getLogger(AvitoDBModelTestPersistenceConfig.class);


    @PostConstruct
    public void initialize() {
        logger.debug("Initialized AVITODB-Model ***TEST*** persistence context. You should not see this in the acutal application");
    }
}
