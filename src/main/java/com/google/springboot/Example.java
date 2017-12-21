/**
 * you should not put your java file in the "default package",the use of
 * "default package" is generally discouraged,since every class from every jar will be read
 * this is the Entry point class and will be used to bootstrap the whole application.
 * It is highly recommended that you put the main entry point class in the root package.
 */
package com.google.springboot;

import com.google.springboot.configuration.PropertyConfiguration;
import com.google.springboot.entity.request.OrgOperationRequest;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

/**
 * '@ComponentScan
   '@SpringBootApplication,@SpringBootApplication is equivalent to using @Configuration,@EnableAutoConfiguration,@ComponentScan with their default attributes
   '@Configuration indicates that this class is a Spring configuration class.
   '@ComponentScan enables component scanning for Spring beans in the package in which the current class is defined
   '@EnableAutoConfiguration triggers Spring Boot's auto-configuration mechanisms,this annotation tells spring
    boot to 'guess' how you will want to configure spring,based on the jar dependencies that you have
    added,
   #####
   this entry class will take care of scanning other Spring configuration classes in all the sub-packages;
 */

/**
 *  It is highly recommended that you put the main entry point class in the root package,so that
 *  @EnableAutoConfiguration and @ComponentScan annotation will scan for Spring beans,
 *  JPA entries etc.If you have an entry point class in a nested package you might need to specify the basePackages to scan for spring components explicitly.
 */

/**
 *  '@EntityScan does not create beans,it is mainly used to scan you entity packages,in this example,spring
 *  boot will scan for JPA entities under the package where OrgOperationRequest.class exists:
 *    '@EntityScan(basePackageClasses = OrgOperationRequest.class)
 */

/**
 *  System properties are set on the Java command line using the -Dpropertyname=value syntax,they can also
 *  be added at runtime using System.setProperty(String key,String value),or via the various
 *  System.getProperties().load() methods
 *
 *  Environment variables are set int the OS,e.g. in Linux export HOME=/User/userName,unlike
 *  properties,may not be set at runtime,System.getenv(String name)
 *
 */

/**
 * we've seen how to break up bean definitions into multiple @Configuration classes and how to reference those
 * beans across @Configuration boundaries,These scenarios have required providing all @Configuration classes
 * to the constructor of a JavaConfigApplicationContext,but what if @Configuration class logically imports the
 * bean definitions defined by another,The @Import annotations provides just this kind of support.
 */
// @EntityScan(basePackageClasses = OrgOperationRequest.class)
@SpringBootApplication
/**
 * Convenient way to quickly register ConfigurationProperties annotated beans with spring
 */
//@EnableConfigurationProperties(PropertyConfiguration.class)
public class Example {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class,args);
    }
}
