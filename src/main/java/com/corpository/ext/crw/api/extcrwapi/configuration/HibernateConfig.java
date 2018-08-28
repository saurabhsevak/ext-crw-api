package com.corpository.ext.crw.api.extcrwapi.configuration;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@Configuration
//@PropertySource("classpath:connection.properties")
//@ComponentScan("com.bluevine.server")
//@EnableTransactionManagement
//@EnableWebMvc
public class HibernateConfig {

    private static final Logger logger = LogManager.getLogger(HibernateConfig.class.getName());

    @Autowired
    private Environment environment;

    @Autowired
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
    	System.out.println("your hibernate method is called");
        logger.printf(Level.DEBUG,"Entry in sessionFactory method");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.bluevine.server.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        logger.printf(Level.DEBUG,"Exit sessionFactory method");
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
    	System.out.println("hibernate datasource method is called");
        logger.printf(Level.DEBUG,"Entry in dataSource method");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        logger.printf(Level.DEBUG,"Exit in dataSource method");
        return dataSource;
    }

    private Properties hibernateProperties() {
    	System.out.println("hibernate properties method is called");
        logger.printf(Level.DEBUG,"Entry in hibernateProperties method");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
        logger.printf(Level.DEBUG,"Entry in hibernateProperties method");
        return properties;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory s) {
    	System.out.println("hibernate transaction management method is called");
        logger.printf(Level.DEBUG,"Entry in transactionManager method");
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        logger.printf(Level.DEBUG,"Entry in transactionManager method");
        return txManager;
    }
}
