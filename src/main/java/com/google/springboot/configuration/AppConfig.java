package com.google.springboot.configuration;

import com.google.springboot.entity.MongoDBDatabaseCondition;
import com.google.springboot.entity.MySQLDatabaseTypeCondition;
import com.google.springboot.entity.impl.JdbcUserDAO;
import com.google.springboot.entity.impl.MongoUserDAO;
import com.google.springboot.interfaces.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    /**
     * MySQLDatabaseTypeCondition and MongoDBDatabaseCondition implements Condition class,
     * if you run the application,such as java -jar myapp.jar -DdbType=MYSQL,only the jdbcUserDAO
     * bean will be registered,but if you set the system property to -DdbType=MONGODB,the MongoUserDAO
     * bean will be registered.
     * @return
     */

    /**
     * By default,the bean name will be the same as the method name
     * @return
     */
    @Bean
    @Conditional(MySQLDatabaseTypeCondition.class)
    public UserDAO jdbcUserDAO() {
        return new JdbcUserDAO();
    }

    @Bean
    @Conditional(MongoDBDatabaseCondition.class)
    public UserDAO mongoUserDAO() {
        return new MongoUserDAO();
    }
}
