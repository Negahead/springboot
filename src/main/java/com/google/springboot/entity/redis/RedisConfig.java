package com.google.springboot.entity.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
//@EnableRedisRepositories
public class RedisConfig {

    /**
     * Database connection pooling is the method used to keep database connections open so they can be reused by others,
     * Typically,opening a database connection is an expensive operation,especially if the database is remote,Pooling keeps
     * the connections active so that,when a connection is later requested,one of the actives ones is used in preference
     * to having to create another one
     * @return
     */
    @Bean
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(50);
        return jedisPoolConfig;
    }

    /**
     * Jedis is one the the connectors supported by spring data redis module.
     * @return
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        // Just so you know,you can not use hostname http://127.0.0.1
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        // turn on connection pooling
        jedisConnectionFactory.setUsePool(true);
        //jedisConnectionFactory.setPassword("2b172b");
        /**
         * in redis-cli:
         *      redis>select 3
         *      All subsequent commands will then use database 3,default is 0
         *      redis>flushdb
         *      This comes in very handy if you want to reset your 'staging' database.
         *  or you can set the database index in you redis.conf file
         *      databases 3
         *
         *
         *  redis get all key names:
         *      redis> keys *
         *
         */
        jedisConnectionFactory.setDatabase(1);
        return jedisConnectionFactory;
    }



    /**
     * The template is in fact the central class of the Redis module due to its rich feature set,the template offers a high-level
     * abstraction for redis interaction.While RedisConnection offers low level methods that accept and return binary values(byte arrays).
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate() {
        final RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }

    @Bean
    ChannelTopic channelTopic() {
        return new ChannelTopic("channel");
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(),channelTopic());
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageSubscriber());
    }
}
