package com.google.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * this annotation helps you working with properties that allow strongly typed bean to govern
 * and validate the configuration of your application.
 * if nested POJO is initialized, a setter is not required.
 * always create a setter for Collections and arrays
 * Maps, as long as they are initialized, need a getter but not necessarily a setter since they can be mutated by the binder.
 */


/*
*  value should be pre-defined in application.properties with name in the standard format
*  now you can inject the PropertyConfiguration bean into other Spring beans and access the properties using getters
* */

/**
 * Now any properties defined in the property file has the prefix "spring.datasource.druid" and the same name as
 * one of the properties are automatically assigned to this object
 */
@Component
/**
 * bean should be active during development,it will only be present in the container during development
 */
// you can optionally define a custom source where we are storing these properties,else the default location application.properties
// is looked up.
//@PropertySource("classpath:configProps.properties")
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class PropertyConfiguration {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private List<String> listValues;
    private Map<String,String> mapHeaders;

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

    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public Map<String, String> getMapHeaders() {
        return mapHeaders;
    }

    public void setMapHeaders(Map<String, String> mapHeaders) {
        this.mapHeaders = mapHeaders;
    }
}
