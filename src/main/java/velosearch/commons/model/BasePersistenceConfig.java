package velosearch.commons.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import velosearch.commons.model.logic.impl.BaseManagerFactoryImpl;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by Viktor Aleksandrov on 19/08/16.
 */


@Configuration
@EnableTransactionManagement(proxyTargetClass = true, mode = AdviceMode.PROXY)
@PropertySource("classpath:avitodb-jdbc.properties")
public class BasePersistenceConfig {
    private static Logger logger = Logger.getLogger(BasePersistenceConfig.class);
    @Resource public Environment environment;


//    @Bean
//    public UdbNamingStrategy udbNamingStrategy() {
//        UdbNamingStrategy udbNamingStrategy = new UdbNamingStrategy();
//        udbNamingStrategy.setUdbSchemaName(environment.getRequiredProperty("jdbc.schema.udb"));
//        udbNamingStrategy.setUdbTables("PPV_PROFILE", "CITY", "COUNTRY", "EDITION_PRICE", "INDIVIDUAL_USER_TYPE", "STATE", "VENDIBLE_UNIT", "PPV_PREVIEW_MODE", "DELIVER_METHOD", "EVENT_TYPE", "LANGUAGE", "USER_PAYMENT_STATUS", "TICKET_TYPE");
//
//        return udbNamingStrategy;
//    }


    @Bean
    public BaseManagerFactoryImpl baseManagerFactory() {
        return new BaseManagerFactoryImpl();
    }


    protected static BasicDataSource createBasicDataSource(Environment environment) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(environment.getRequiredProperty("jdbc.url"));
        ds.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        ds.setPoolPreparedStatements(Boolean.valueOf(environment.getRequiredProperty("jdbc.pool.ps")));
        ds.setMaxOpenPreparedStatements(Integer.valueOf(environment.getRequiredProperty("jdbc.pool.maxps")));
        ds.setMaxTotal(Integer.valueOf(environment.getRequiredProperty("jdbc.pool.maxActive")));
        ds.setMaxIdle(Integer.valueOf(environment.getRequiredProperty("jdbc.pool.maxIdle")));
        ds.setMinIdle(Integer.valueOf(environment.getRequiredProperty("jdbc.pool.minIdle")));
        ds.setValidationQuery(environment.getRequiredProperty("jdbc.pool.validationquery"));
        ds.setValidationQueryTimeout(30);
        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(true);
        ds.setTestWhileIdle(true);
        ds.setMaxConnLifetimeMillis(600000);            // 10 min. How long IDLE can connection live
        ds.setInitialSize(10);
        ds.setTimeBetweenEvictionRunsMillis(30000);     // 30 sec. Time to sleep between runs of the IDLE object evictor thread
        ds.setMinEvictableIdleTimeMillis(600000);       // 10 min. After that time IDLE object will be evicted from the pool

        return ds;
    }


    protected static Properties getHibernateProperties(Environment environment) {
        Properties properties = new Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.put("hibernate.dialect", "com.enigmabridge.hibernate.dialect.SQLiteDialect");
        properties.put("hibernate.max_fetch_depth", 4);
        properties.put("hibernate.jdbc.batch_size", 30);
        properties.put("hibernate.bytecode.use_reflection_optimizer", true);
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", false);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        properties.put("hibernate.query.plan_cache_max_strong_references", 64);           // memory optimizations due to HHH-5300
        properties.put("hibernate.query.plan_cache_max_soft_references", 1024);           // memory optimizations due to HHH-5300
        properties.put("hibernate.jdbc.use_scrollable_resultset", true);
        properties.put("hibernate.generate_statistics", false);

        //properties.put("org.hibernate.flushMode", "COMMIT");                              // will flush changes only on commit to avoid flushing on selects
        properties.put("hibernate.enable_lazy_load_no_trans", true);                      // No LazyInitializationException anymore!
        return properties;
    }
}

