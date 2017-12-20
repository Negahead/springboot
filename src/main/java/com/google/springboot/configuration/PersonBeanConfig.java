package com.google.springboot.configuration;

import com.google.springboot.entity.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonBeanConfig {
    @Bean
    public Person getPerson() {
        return new Person();
    }
}
