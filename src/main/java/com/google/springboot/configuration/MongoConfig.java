package com.google.springboot.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.*;

@Configuration
@EnableMongoRepositories
public class MongoConfig{
    @Autowired
    ClusterProperties clusterProperties;


    @Bean("connections")
    public Map<String,MongoTemplate> getConnections() {
        Map<String,MongoTemplate> connections = new HashMap<>();
        for(Map.Entry<String,Connection> c : clusterProperties.getCluster().entrySet()) {
            String connectionName = c.getKey();
            Connection connection = c.getValue();
            if(connection.getUsername() != null && connection.getPassword()!=null) {
                ServerAddress serverAddress = new ServerAddress(connection.getHost(), connection.getPort());
                MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(MongoCredential.createMongoCRCredential(connection.getUsername(), connection.getDatabase(), connection.getPassword().toCharArray())));
            } else {
                MongoClient mongoClient = new MongoClient(connection.getHost(), connection.getPort());
                connections.put(connectionName,new MongoTemplate(mongoClient,connection.getDatabase()));
            }
        }
        return connections;
    }
}
