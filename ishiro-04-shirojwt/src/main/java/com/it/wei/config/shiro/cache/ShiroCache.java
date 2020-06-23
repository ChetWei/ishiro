package com.it.wei.config.shiro.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/22 21:00
 * @description:自定义shiro缓存，实现shiro的Cache接口
 */
@Slf4j
public class ShiroCache<K,V> implements org.apache.shiro.cache.Cache<K,V> {

    //spring的缓存管理器
    private CacheManager springCacheManager;
    //spring的缓存对象
    private Cache springCache;
    //
    private String cacheName;

    //通过构造方法注入
    public ShiroCache(CacheManager springCacheManager, String cacheName) {
        this.springCacheManager = springCacheManager;
        //使用缓存管理器通过缓存名称获取缓存对象
        this.cacheName = cacheName;
        this.springCache = springCacheManager.getCache(cacheName);
    }

    @Override
    public V get(K k) throws CacheException {
        log.warn("从缓存中取key:{}",k);
        Cache.ValueWrapper valueWrapper = springCache.get(k);
        if(valueWrapper == null){
            return null;
        }
        return (V) valueWrapper.get();
    }

    @Override
    public V put(K k, V v) throws CacheException {
        log.warn("存入缓存key:{}",k);
        springCache.put(k,v);
        return v;
    }

    //退出的时候清除用户缓存
    @Override
    public V remove(K k) throws CacheException {
        log.warn("清除缓存key:{}",k);
        V v = this.get(k);
        springCache.evict(k);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        log.warn("清空name:{}的缓存",cacheName);
        springCache.clear();
    }

    @Override
    public int size() {
        log.warn("获取缓存size");
        return keys().size();

    }

    @Override
    public Set<K> keys() {
        log.warn("获取所有缓存keys");
         return (Set<K>) springCacheManager.getCacheNames();

    }

    @Override
    public Collection<V> values() {
        log.warn("获取所有values",cacheName);
        ArrayList<V> list = new ArrayList<>();
        Set<K> keys = keys();
        for(K k:keys){
            list.add(this.get(k));
        }
        return list;
    }


}