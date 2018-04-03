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
@EnableMongoRepositories(basePackages = {"com.google.springboot.entity.mongo"})
public class MongoConfig{
    @Autowired
    ClusterProperties clusterProperties;


    //https://www.petrikainulainen.net/programming/solr/spring-data-solr-tutorial-dynamic-queries/

    @Bean("connections")
    public Map<String,MongoClient> getConnections() {
        Map<String,MongoClient> connections = new HashMap<>();
        for(Map.Entry<String,Connection> c : clusterProperties.getCluster().entrySet()) {
            String connectionName = c.getKey();
            Connection connection = c.getValue();
            if(connection.getUsername() != null&& !"".equals(connection.getUsername()) &&
                    connection.getPassword()!=null && !"".equals(connection.getPassword())) {
                ServerAddress serverAddress = new ServerAddress(connection.getHost(), connection.getPort());
                MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(MongoCredential.createMongoCRCredential(connection.getUsername(), connection.getDatabase(), connection.getPassword().toCharArray())));
                connections.put(connectionName,mongoClient);
            } else {
                MongoClient mongoClient = new MongoClient(connection.getHost(), connection.getPort());
                connections.put(connectionName,mongoClient);
            }
        }
        return connections;
    }
}
