package com.it.wei.config.redis;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/22 21:27
 * @description:
 */

//@Configuration
public class RedisConfig {
/*
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        //开启事务支持
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //redisTemplate.setKeySerializer(stringRedisSerializer()); //字符串的key序列化方式
        //redisTemplate.setHashKeySerializer(stringRedisSerializer()); //hash key的序列化方式
        //开启事务支持
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }*/


}