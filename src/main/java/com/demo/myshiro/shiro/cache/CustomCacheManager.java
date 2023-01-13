package com.demo.myshiro.shiro.cache;

import com.demo.myshiro.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomCacheManager implements CacheManager {

    @Autowired
    private RedisService redisService;
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println("name:  "+cacheName);
        return new CustomCache<K,V>(redisService);
    }
}
