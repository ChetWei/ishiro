package com.it.wei.common;

import com.it.wei.config.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;



/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 16:14
 * @description:
 */
public class ShiroUtil {

    static {
        //1初始化shiro安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //2.设置用户的权限信息到安全管理器
        //IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        Realm realm = new ShiroRealm();
        defaultSecurityManager.setRealm(realm);
        //3设置缓存管理器
        //CacheManager cacheManager = new MemoryConstrainedCacheManager();
        CacheManager cacheManager = new EhCacheManager();
        defaultSecurityManager.setCacheManager(cacheManager);

        //4使用SecurityUtils将defaultSecurityManager设置到运行环境中
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    public static Subject login(String username,String password){
        //4、获得一个subject实例
        Subject subject = SecurityUtils.getSubject();
        //5、创建用于认证的token，记录用户认证的账号密码
        AuthenticationToken token = new UsernamePasswordToken(username,password);
        //6 主体进行登录，登录的时候进行认证检查,如果失败会报相关错误
        subject.login(token);
        System.out.println("用户认证状态"+subject.isAuthenticated());

        return subject;
    }


}