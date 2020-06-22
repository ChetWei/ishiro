package com.it.wei.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/22 21:00
 * @description:自定义shiro缓存，实现shiro的Cache接口
 */
public class ShiroCache<K,V> implements Cache<K,V> {

    private RedisTemplate<K, V> redisTemplate;
    private String cacheName;

    //通过构造方法注入
    public ShiroCache(RedisTemplate<K, V> redisTemplate, String cacheName) {
        this.redisTemplate = redisTemplate;
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException {

        return (V) redisTemplate.opsForHash().get((K) cacheName, k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForHash().put((K) cacheName,k,v);
        return v;
    }

    //退出的时候清除用户缓存
    @Override
    public V remove(K k) throws CacheException {
        V value = get(k);
        redisTemplate.opsForHash().delete((K) cacheName,k);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete((K) cacheName);
    }

    @Override
    public int size() {
        return redisTemplate.opsForHash().size((K) cacheName).intValue();
    }

    @Override
    public Set<K> keys() {

        return (Set<K>) redisTemplate.opsForHash().keys((K) cacheName);

    }

    @Override
    public Collection<V> values() {
      return (Collection<V>) redisTemplate.opsForHash().values((K) cacheName);
    }

    //Hset  key 固定值  k 用户相关信息  v 值
}