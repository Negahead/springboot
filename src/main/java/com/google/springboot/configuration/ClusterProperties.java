package com.google.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "spring.mongo")
public class ClusterProperties {
    private Map<String,Connection> cluster = new HashMap<>();

    public Map<String, Connection> getCluster() {
        return cluster;
    }

    public void setCluster(Map<String, Connection> cluster) {
        this.cluster = cluster;
    }
}
