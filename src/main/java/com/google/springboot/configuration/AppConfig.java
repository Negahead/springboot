package com.google.springboot.configuration;

import com.google.springboot.entity.impl.MongoDBDatabaseCondition;
import com.google.springboot.entity.impl.MySQLDatabaseTypeCondition;
import com.google.springboot.entity.impl.JdbcUserDAO;
import com.google.springboot.entity.impl.MongoUserDAO;
import com.google.springboot.interfaces.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Annotating a class with the @Configuration annotation indicates that the class will be used by JavaConfig
 * as a source if bean definition.
 */
@Configuration
public class AppConfig {
    /**
     * MySQLDatabaseTypeCondition and MongoDBDatabaseCondition implements Condition class,
     * if you run the application,such as java -jar myapp.jar -DdbType=MYSQL,only the jdbcUserDAO
     * bean will be registered,but if you set the system property to -DdbType=MONGODB,the MongoUserDAO
     * bean will be registered.
     *
     */

    /**
     * By default,the bean name will be the same as the method name,method-level,
     * '@Bean' is used to explicitly declare a bean,rather than letting spring
     * do it automatically.It decouples the declaration of the bean from the class definition
     * and lets you create and configure beans exactly how you choose.
     */

    /**
     * The @Component annotation marks a java class as a bean so the component-scanning mechanism of spring
     * can pick it up and pull it into the application context
     *
     * '@Service' annotation is a specialization of the component annotation,it doesn't currently provide any additional
     * behavior over the @Component annotation,it specifies intent better.
     *
     * '@Component' are used to auto-detect and auto-configure beans using classpath scanning,
     *
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
