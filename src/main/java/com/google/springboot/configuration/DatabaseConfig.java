package com.google.springboot.configuration;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
/**
 * ' @Configuration is also a @Component
 */


/**
 *  The JavaEE platform provides the Java Persistence API(JPA) specification,which is an Object Relational Mapping
 *  framework,Hibernate and EclipseLink are the most popular JPA implementations.There are other popular persistence frameworks
 *  such as Mybatis mad JOOQ,that are SQL forced.
 *  Spring provides a nice abstraction on top of the JDBC API,using JdbcTemplate,and provides great transaction management capabilities using
 *  annotation-based approach
 */
@Configuration
/**
 * Scan Mapper interface,Only interface with at least one method will be registered
 */
@MapperScan(basePackages = "com.google.springboot.mapper")
public class DatabaseConfig {
//    @Value("${spring.datasource.druid.url}")
//    private String url;
//    @Value("${spring.datasource.druid.username}")
//    private String userName;
//    @Value("${spring.datasource.druid.password}")
//    private String password;
//    @Value("${spring.datasource.druid.driver-class-name}")
//    private String driver;

    @Autowired
    PropertyConfiguration propertyConfiguration;


    @Bean
    // use Profile,you can register multiple beans of the same type and associate them with
    // one or more profiles.when you run the application,you can activate
    // -Dspring.profiles.active=DEV
    //@Profile("dev")
    //@Profile("!dev") : the component is activated only if "dev" profile is not active
    public DruidDataSource devDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyConfiguration.getDriverClassName());
        druidDataSource.setUrl(propertyConfiguration.getUrl());
        druidDataSource.setUsername(propertyConfiguration.getUsername());
        druidDataSource.setPassword(propertyConfiguration.getPassword());
        return druidDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(devDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(devDataSource());
        //sessionFactoryBean.setConfigLocation();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
