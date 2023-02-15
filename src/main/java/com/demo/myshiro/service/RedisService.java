package com.demo.myshiro.service;

import io.lettuce.core.dynamic.domain.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.KeyBoundCursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.TimeUnit;


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

    public Long getExpire(String key,TimeUnit timeUnit) throws Exception {
        if(key != null){
            return  redisTemplate.getExpire(key,timeUnit);
        }else{
            throw new Exception("key值不能为空");
        }

    }

    public void put(String key, Object value, long time, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,time,timeUnit);
    }

    public String get(String key){
        String  value = (String) redisTemplate.opsForValue().get(key);
        return value;
    }

    public boolean delete(String key){
        if(!StringUtils.isEmpty(key)){
            return redisTemplate.delete(key);
        }
        return false;

    }
}
