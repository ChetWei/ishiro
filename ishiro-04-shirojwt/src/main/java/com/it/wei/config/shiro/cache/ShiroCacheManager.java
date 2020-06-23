package com.it.wei.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.cache.CacheManager;


/**
 * @auther: chetwei@163.com
 * @date: 2020/6/22 21:06
 * @description:
 */
public class ShiroCacheManager<K, V> implements org.apache.shiro.cache.CacheManager{

    //使用spring的CacheManager
    private CacheManager springCacheManager;

    //通过构造方法注入
    public ShiroCacheManager(CacheManager springCacheManager) {
        this.springCacheManager = springCacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new ShiroCache(springCacheManager,cacheName);
    }
}