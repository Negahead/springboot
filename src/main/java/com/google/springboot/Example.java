/**
 * you should not put your java file in the "default package",the use of
 * "default package" is generally discouraged,since every class from every jar will be read
 * this is the Entry point class and will be used to bootstrap the whole application.
 * It is highly recommended that you put the main entry point class in the root package.if you don't you
 */
package com.google.springboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

// The key difference between a traditional spring MVC controller and the RESTful com.google.springboot.controller.web com.google.springboot.controller.service contoller
// is the way the HTTP response body is created,while the traditional MVC controller relies on the View technology
// the RESTfu; com.google.springboot.controller.web com.google.springboot.controller.service controller simply returns the object and the object is written directly to the HTTP
// response as JSON/XML
// @RestController
// @EnableAutoConfiguration
// @RequestMapping(path = "/home",method = RequestMethod.POST)
/**
 * @ComponentScan
   @SpringBootApplication,@SpringBootApplication is equivalent to using @Configuration,@EnableAutoConfiguration,@ComponentScan with their default attributes
   @Configuration indicates that this class is a Spring configuration class.
   @ComponentScan enables component scanning for Spring beans in the package in which the current class is defined
   @EnableAutoConfiguration triggers Spring Boot's auto-configuration mechanisms
   this entry class will take care of scanning other Spring configuration classes in all the sub-packages;
 */
@SpringBootApplication
public class Example {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class,args);
        Syssf
    }
}
