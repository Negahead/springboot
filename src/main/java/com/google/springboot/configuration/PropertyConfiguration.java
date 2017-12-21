package com.google.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
/**
 * this annotation helps you working with properties that allow strongly typed bean to govern
 * and validate the configuration of your application.
 * if nested POJO is initialized, a setter is not required.
 * always create a setter for Collections and arrays
 * Maps, as long as they are initialized, need a getter but not necessarily a setter since they can be mutated by the binder.
 */
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class PropertyConfiguration {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
