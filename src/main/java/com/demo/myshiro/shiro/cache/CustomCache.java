package com.demo.myshiro.shiro.cache;

import com.alibaba.fastjson.JSON;
import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.service.RedisService;
import com.demo.myshiro.util.JwtTokenUtil;
import com.demo.myshiro.util.SpringUtil;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CustomCache<K,V> implements Cache<K,V> {

    private RedisService redisService;

    private String cacheKey = Constant.IDENTITY_CACHE_KEY;

    private long expireTime = 24;

    public CustomCache(RedisService redisService){
        this.redisService = redisService;
    }

    @Override
    public V get(K k) throws CacheException {
        if( null == k){
            return null;
        }
        String redisCacheKey = getRedisCacheKey(k.toString());
        String value = redisService.get(k.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = JSON.parseObject(value, SimpleAuthorizationInfo.class);
        return (V) simpleAuthorizationInfo;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if( null == k){
            return null;
        }
        String redisCacheKey = getRedisCacheKey(k.toString());
        redisService.put(redisCacheKey,JSON.toJSONString(v),expireTime, TimeUnit.HOURS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

//    @Override
//    public V remove(K k) throws CacheException {
//        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
//        redisTemplate.opsForHash().delete(cacheName,k.toString());
//        return null;
//    }
//
//    @Override
//    public void clear() throws CacheException {
//        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
//        redisTemplate.delete(cacheName);
//    }
//
//    @Override
//    public int size() {
//        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
//        Long size = redisTemplate.opsForHash().size(cacheName);
//        return Math.toIntExact(size);
//    }
//
//    @Override
//    public Set<K> keys() {
//        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
//        Set keys = redisTemplate.opsForHash().keys(cacheName);
//        return keys;
//    }
//
//    @Override
//    public Collection<V> values() {
//        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
//        List values = redisTemplate.opsForHash().values(cacheName);
//        return values;
//    }

    private String getRedisCacheKey(String token){
        String userId = JwtTokenUtil.getUserId(token);
        return this.cacheKey + userId;
    }
}
