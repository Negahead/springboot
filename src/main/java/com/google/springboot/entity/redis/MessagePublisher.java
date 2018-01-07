package com.google.springboot.entity.redis;

public interface MessagePublisher {
    void publish(final String message);
}
