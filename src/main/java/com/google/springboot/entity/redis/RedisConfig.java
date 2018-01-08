package com.google.springboot.entity.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    /**
     * Jedis is one the the connectors supported by spring data redis module.
     * @return
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setPassword("2b172b");
        /**
         * in redis-cli:
         *      redis>select 3
         *      All subsequent commands will then use database 3,default is 0
         *      redis>flushdb
         *      This comes in very handy if you want to reset your 'staging' database.
         *  or you can set the database index in you redis.conf file
         *      databases 3
         *
         */
        jedisConnectionFactory.setDatabase(0);
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
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
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
