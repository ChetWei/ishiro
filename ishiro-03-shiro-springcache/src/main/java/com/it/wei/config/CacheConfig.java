package com.it.wei.config;

import com.it.wei.config.shiro.cache.ShiroCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author: weimingan
 * @Date: 2020/6/23 09:24
 * @Description:
 */
@Configuration
public class CacheConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**org.springframework.cache.CacheManager
     * SpringCacheManger 缓存管理器
     *  使用的缓存是redis
     * @return
     */
    @Bean(name = "springCacheManager")
    public CacheManager springCacheManager(){
        //创建一个redis缓存的默认配置
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig();
        //配置序列化规则
        //设置默认的过期时间 -1 永久有效
        conf = conf.entryTtl(Duration.ofMinutes(-1));
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(conf)
                .build();
        return cacheManager;
    }


    /**
     * shiro 缓存管理器
     * @return
     */
    @Bean(name = "shiroCacheManager")
    public ShiroCacheManager shiroCacheManager(){
        //创建一个redis缓存的默认配置
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig();
        //配置序列化规则
        //设置默认的过期时间 -1 永久有效
        conf = conf.entryTtl(Duration.ofMinutes(5));
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(conf)
                .build();
        //使用spring的缓存管理器
        return new ShiroCacheManager(cacheManager);
    }



}