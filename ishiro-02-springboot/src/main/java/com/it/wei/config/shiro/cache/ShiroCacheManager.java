package com.it.wei.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/22 21:06
 * @description:
 */
public class ShiroCacheManager<K, V> implements CacheManager {

    private RedisTemplate<K, V> redisTemplate;

    //通过构造方法注入redisTemplate
    public ShiroCacheManager(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new ShiroCache(redisTemplate,cacheName);
    }
}