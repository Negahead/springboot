package com.google.springboot.configuration;

import com.google.springboot.entity.POJO.Mercedes;
import com.google.springboot.entity.POJO.Tree;
import com.google.springboot.entity.impl.*;
import com.google.springboot.interfaces.UserDAO;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Annotating a class with the @Configuration annotation indicates that the class will be used by JavaConfig
 * as a source of bean definition.
 */
@Configuration
@Import(PersonBeanConfig.class)
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


    /**
     * ' @Bean(name="your bean name")
     *
     *
     *   ====>
     *
     * ' @Autowire
     * ' @Qualifier("your bean name")
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

    @Bean
    @Conditional(SomeClassPresentCondition.class)
    public Tree getTree() {
        Tree tree = new Tree();
        tree.setHeight(3000);
        tree.setName("European larch");
        return tree;
    }

    @Bean
    @Conditional(PropertyCondition.class)
    public Mercedes getMercedes() {
        Mercedes mercedes = new Mercedes();
        mercedes.setName("Mercedes-Benz");
        mercedes.setPrice(133455.6456);
        return mercedes;
    }

    @Bean(name = "mongoClient")
    public MongoClient getMongoClient() {
        return new MongoClient("localhost",27017);
    }



}
