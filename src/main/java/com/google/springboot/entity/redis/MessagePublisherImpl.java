package com.google.springboot.entity.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisherImpl implements MessagePublisher {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    ChannelTopic channelTopic;

    public MessagePublisherImpl() {

    }

    public MessagePublisherImpl(final RedisTemplate<String,Object> redisTemplate,final ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    @Override
    public void publish(final String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(),message);
    }

}
