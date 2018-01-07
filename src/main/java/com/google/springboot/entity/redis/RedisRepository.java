package com.google.springboot.entity.redis;

import java.util.Map;

public interface RedisRepository {
    Map<Object,Object> findAllMovies();

    void add(Movie movie);

    void delete(String id);

    String findMovie(String id);
}
