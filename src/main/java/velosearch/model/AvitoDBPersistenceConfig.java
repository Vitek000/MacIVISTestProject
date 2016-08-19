package velosearch.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Viktor Aleksandrov on 19/08/16.
 */



import com.eastview.commons.model.persistence.hibernate.DAOHibernateFactory;
import com.eastview.model.domain.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

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
@ImportResource("classpath:spring-udb-model.xml")
@Import(BasePersistenceConfig.class)
@ComponentScan(basePackages = { "com.eastview.model.domain", "com.eastview.model.logic", "com.eastview.model.persistence", "com.eastview.model.udb" })
public class AvitoDBPersistenceConfig {
    private static Logger logger = Logger.getLogger(AvitoDBPersistenceConfig.class);
    @Resource public Environment environment;
    //@Autowired private UdbNamingStrategy udbNamingStrategy;


    @PostConstruct
    public void initialize() {
        logger.info("Initialized UDB persistence context");
    }


    @Bean(name = "udb_DataSource")
    public DataSource udbDataSource() {
        BasicDataSource ds = BasePersistenceConfig.createBasicDataSource(environment);
        ds.setUsername(environment.getRequiredProperty("jdbc.username.udb"));
        ds.setPassword(environment.getRequiredProperty("jdbc.password.udb"));

        return ds;
    }


    @Bean(name = "udb_SessionFactory")
    public SessionFactory udbSessionFactory() throws IOException {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(udbDataSource());
        sf.setNamingStrategy(udbNamingStrategy);
        sf.setHibernateProperties(BasePersistenceConfig.getHibernateProperties(environment));

        // don't use setAnnotatedPackages cause we have udb_app also with different entity list
        // todo: refactor package names to be able to use setAnnotatedPackages
        sf.setAnnotatedClasses(City.class,
                Country.class,
                State.class,
                VendibleUnit.class,
                EditionPrice.class,
                IndividualUserType.class,
                PpvProfile.class,
                EditionSysInfo.class,
                PpvPreviewMode.class,
                UserProfile.class,
                Preference.class,
                ApplicationDatabase.class,
                UserGroup.class,
                UserInGroup.class,
                UserGroupType.class,
                ArticleFormatType.class,
                EditionType.class,
                EditionLoadingPeriod.class,
                EditionCirculation.class,
                ArticlePdfFile.class,
                ArticleSellDeprecated.class,
                DeliverMethod.class,
                EventType.class,
                AccessAttributeType.class,
                AccessPermission.class,
                PermissionAttribute.class,
                UserAccessGroup.class,
                AutoLoadType.class,
                Language.class,
                UserPaymentStatus.class,
                EditionDataFormat.class,
                CalendarDateType.class,
                ClassificatorKind.class,
                Classificator.class,
                ClassificatorsTypeHierarhy.class,
                ClassificatorType.class,
                GroupedBookRuSeries.class,
                BookAuthor.class,
                EditionLanguage.class,
                Article.class,
                DeletedArticle.class,
                Issue.class,
                DocumentSwfFile.class,
                OfficeLoadTemplate.class,
                CollectionSection.class,
                CounterTaskState.class,
                TicketType.class,
                EditionFullImageAccessType.class,
                SubscriberGroup.class,
                CounterUserProperties.class,
                ArticleImageFile.class,
                Converter.class,
                Encoding.class,
                User.class,
                EditionLoadType.class,
                UserAuthenType.class,
                Person.class,
                UserType.class,
                UserSubscription.class,
                EditionCategoryEn.class,
                EditionCategoryRu.class,
                SubscriptionType.class,
                UserSubscriptionEntity.class,
                Application.class,
                EditionIssueHistoryStatus.class,
                EditionIssueStatus.class,
                IpRecord.class,
                UserSettings.class,
                CounterGenerateTask.class,
                SubscriptionTemplate.class,
                SubscriptionTemplateRecord.class,
                SubscriptionTemplateParameter.class,
                SubscriptionTemplateParameterType.class,
                UserProfileRestrictions.class,
                UserEditions.class,
                EditionCategoryType.class,
                EditionSubCategory.class,
                ArticleAttachment.class,
                BookSerie.class,
                PendingIssue.class,
                EwCustomer.class,
                EwProduct.class,
                EwUnfinishedSubscription.class,
                UserSubscriptionEwSales.class,
                EwUnfinishedMessageType.class,
                EwUnfinishedMessage.class,
                EwNotification.class,
                TitleLanguageData.class,
                Title.class);

        sf.afterPropertiesSet();
        return sf.getObject();
    }


    @Bean(name = "udb_TransactionManager")
    public HibernateTransactionManager udbTransactionManager() throws IOException {
        return new HibernateTransactionManager(udbSessionFactory());
    }


    @Bean
    public DAOHibernateFactory hibernateDAOFactory() throws IOException {
        DAOHibernateFactory f = new DAOHibernateFactory();
        f.setSessionFactory(udbSessionFactory());
        return f;
    }
}
