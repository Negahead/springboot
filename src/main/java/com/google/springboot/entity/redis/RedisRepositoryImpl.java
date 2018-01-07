package com.google.springboot.entity.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository{
    private static final String KEY = "myhash";
    private HashOperations hashOperations;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    /**
     * The PostConstruct annotation is used on a method that needs to be executed
     * after dependency injection is done to perform any initialization.
     */
    @PostConstruct
    private void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<Object, Object> findAllMovies() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void add(Movie movie) {
        hashOperations.put(KEY,movie.getId(),movie.getName());
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY,id);
    }

    @Override
    public String findMovie(String id) {
        return (String) hashOperations.get(KEY,id);
    }
}
