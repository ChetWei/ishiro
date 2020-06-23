package com.it.wei.config.shiro;


import com.it.wei.config.shiro.cache.ShiroCacheManager;
import com.it.wei.config.shiro.session.ShiroSessionDAO;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:37
 * @description:
 */
@Configuration
public class ShiroConfig {

    @Autowired
    @Qualifier("shiroCacheManager")
    private ShiroCacheManager shiroCacheManager;


    @Bean("customerRealm")
    public CustomerRealm customerRealm() {
        return new CustomerRealm();
    }


    //配置会话管理器
    @Bean
    public SessionManager sessionManager(){
        //web环境的会话管理器
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        //使用自定义的会话 DAO
        ShiroSessionDAO sessionDAO = new ShiroSessionDAO(shiroCacheManager);
        //注入自定义的sessionDAO
        webSessionManager.setSessionDAO(sessionDAO);
        return webSessionManager;
    }



    /**
     * 配置安全管理器
     *
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义的realm
        securityManager.setRealm(customerRealm());
        //注入缓存管理器
        securityManager.setCacheManager(shiroCacheManager);
        //注入会话管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //配置权限 过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        //注入安全管理器
        filter.setSecurityManager(securityManager());
        //未认证的跳转地址
        Map<String, String> chain = new LinkedHashMap<>();
        chain.put("/login", "anon");

        filter.setFilterChainDefinitionMap(chain);
        return filter;
    }




    /**
     * 启用shiro注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

}