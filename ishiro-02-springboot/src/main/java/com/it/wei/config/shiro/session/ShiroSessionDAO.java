package com.it.wei.config.shiro.session;

import com.it.wei.config.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/23 00:02
 * @description:使用redis实现会话的管理
 */
public class ShiroSessionDAO extends CachingSessionDAO {


    public ShiroSessionDAO(CacheManager cacheManager) {
        this.setCacheManager(cacheManager);
    }

    @Override
    protected Serializable doCreate(Session session) {
        //生成会话的唯一id
        Serializable sessionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        //this.assignSessionId(session, sessionId);
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