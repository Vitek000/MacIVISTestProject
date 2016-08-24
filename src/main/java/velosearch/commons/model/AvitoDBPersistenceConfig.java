package velosearch.commons.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Viktor Aleksandrov on 19/08/16.
 */



import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import velosearch.model.domain.AvitoMessageEntity;
import velosearch.commons.model.persistence.hibernate.DAOHibernateFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * UdbModelPersistenceConfig
 * <B>Date:</B> 04/08/16 <BR/>
 * <B>Time:</B> 16:07
 *
 * @author Anton Stremoukhov
 */
@Configuration
@ImportResource("classpath:avitodb-spring-model.xml")
@Import(BasePersistenceConfig.class)
@ComponentScan(basePackages = { "velosearch.model.domain", "velosearch.model.logic", "velosearch.model.persistence"/*, "com.eastview.model.udb"*/ })
public class AvitoDBPersistenceConfig {
    private static Logger logger = Logger.getLogger(AvitoDBPersistenceConfig.class);
    @Resource public Environment environment;
    //@Autowired private UdbNamingStrategy udbNamingStrategy;


    @PostConstruct
    public void initialize() {
        logger.info("Initialized UDB persistence context");
    }


    @Bean(name = "avitodb_DataSource")
    public DataSource udbDataSource() {
        BasicDataSource ds = BasePersistenceConfig.createBasicDataSource(environment);
        ds.setUsername(environment.getRequiredProperty("jdbc.username.avitodb"));
        ds.setPassword(environment.getRequiredProperty("jdbc.password.avitodb"));

        return ds;
    }

    @Bean(name = "avitodb_SessionFactory")
    public SessionFactory udbSessionFactory() throws IOException {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(udbDataSource());
        //sf.setNamingStrategy(udbNamingStrategy);
        sf.setHibernateProperties(BasePersistenceConfig.getHibernateProperties(environment));

        // don't use setAnnotatedPackages cause we have udb_app also with different entity list
        // todo: refactor package names to be able to use setAnnotatedPackages
        sf.setAnnotatedClasses(AvitoMessageEntity.class
               );

        sf.afterPropertiesSet();
        return sf.getObject();
    }


    @Bean(name = "avitodb_TransactionManager")
    public HibernateTransactionManager avitodbTransactionManager() throws IOException {
        return new HibernateTransactionManager(udbSessionFactory());
    }


    @Bean
    public DAOHibernateFactory hibernateDAOFactory() throws IOException {
        DAOHibernateFactory f = new DAOHibernateFactory();
        f.setSessionFactory(udbSessionFactory());
        return f;
    }
}
