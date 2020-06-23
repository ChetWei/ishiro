package com.it.wei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author: weimingan
 * @Date: 2020/6/23 09:24
 * @Description:
 */
public class CacheConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * redis和springcache整合的缓存管理器
     * @return
     */
    @Bean(name = "redisCacheManager")
    public CacheManager redisCacheManager(){
        //创建一个redis缓存的默认配置
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig();
        //配置序列化规则
        //设置默认的过期时间
        conf = conf.entryTtl(Duration.ofMillis(-1));
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(conf)
                .build();
        return cacheManager;
    }

}