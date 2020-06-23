package com.it.wei.auth.config;


import com.it.wei.auth.shiro.JWTRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:37
 * @description:
 */
@Configuration
public class ShiroConfig {


    /**
     * 自定义releam
     * @return
     */
    @Bean("customerRealm")
    public JWTRealm customerRealm() {
        return new JWTRealm();
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