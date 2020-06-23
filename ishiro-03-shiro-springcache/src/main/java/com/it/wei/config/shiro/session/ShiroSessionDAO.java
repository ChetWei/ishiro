package com.it.wei.config.shiro.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;
import java.util.UUID;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/23 00:02
 * @description:使用redis实现会话的管理
 */
@Slf4j
public class ShiroSessionDAO extends CachingSessionDAO {


    //注入自定义的缓存管理器
    public ShiroSessionDAO(CacheManager cacheManager) {
        this.setCacheManager(cacheManager);
    }

    @Override
    protected Serializable doCreate(Session session) {
        //生成会话的唯一id
        Serializable sessionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.assignSessionId(session, sessionId);
        log.warn("生成会话id:{}",sessionId);
        return sessionId;

    }

    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }



    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }
}