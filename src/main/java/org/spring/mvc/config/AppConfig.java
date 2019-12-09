package org.spring.mvc.config;


import org.hibernate.SessionFactory;
import org.spring.mvc.util.PropertyReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages =
        "org.spring.mvc")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PropertyReader.getProperty("hibernate.connection.driver_class"));
        dataSource.setUrl(PropertyReader.getProperty("hibernate.connection.url"));
        dataSource.setUsername(PropertyReader.getProperty("hibernate.connection.username"));
        dataSource.setPassword(PropertyReader.getProperty("hibernate.connection.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", PropertyReader.getProperty("hibernate.dialect"));
        hibernateProp.put("hibernate.format_sql", PropertyReader.getProperty("hibernate.format_sql"));
        hibernateProp.put("hibernate.use_sql_comments", PropertyReader.getProperty("hibernate.use_sql_comments"));
        hibernateProp.put("hibernate.show_sql", PropertyReader.getProperty("hibernate.show_sql"));
        hibernateProp.put("hibernate.hbm2ddl.auto", PropertyReader.getProperty("hibernate.hbm2ddl.auto"));
        return hibernateProp;
    }

    @Bean
    public SessionFactory sessionFactory()
            throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("org.spring.mvc.domain");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager()
            throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }


}
