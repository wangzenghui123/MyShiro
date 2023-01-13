package com.demo.myshiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean hasKey(String key){
        if(null != key){
            return redisTemplate.hasKey(key);
        }
        return false;
    }
}
