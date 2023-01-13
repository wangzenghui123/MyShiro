package com.demo.myshiro.shiro.cache;

import com.demo.myshiro.util.SpringUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public CustomCache(){}

    public CustomCache(String cacheName){
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key "+k);
        System.out.println("cachename:" + cacheName);
        //cachename:com.demo.myshiro.shiro.realm.CustomRealm.authorizationCache
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        V value =(V) redisTemplate.opsForHash().get(cacheName,k.toString());
        return value;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key :"+k);
        System.out.println("put value: "+v);
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        redisTemplate.opsForHash().put(cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        redisTemplate.opsForHash().delete(cacheName,k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        redisTemplate.delete(cacheName);
    }

    @Override
    public int size() {
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        Long size = redisTemplate.opsForHash().size(cacheName);
        return Math.toIntExact(size);
    }

    @Override
    public Set<K> keys() {
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        Set keys = redisTemplate.opsForHash().keys(cacheName);
        return keys;
    }

    @Override
    public Collection<V> values() {
        RedisTemplate redisTemplate = (RedisTemplate) new SpringUtil().getBean("redisTemplate");
        List values = redisTemplate.opsForHash().values(cacheName);
        return values;
    }
}
