package com.it.wei;

import com.it.wei.common.MD5Util;
import com.it.wei.common.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 15:34
 * @description:
 */
public class ShiroTest {
    @Test
    public void test(){
        //1初始化shiro安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //2.设置用户的权限信息到安全管理器
        Realm realm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(realm);
        //3使用SecurityUtils将defaultSecurityManager设置到运行环境中
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //4、获得一个subject实例
        Subject subject = SecurityUtils.getSubject();
        //5、创建用于认证的token，记录用户认证的账号密码
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","123456");
        //6 主体进行登录，登录的时候进行认证检查,如果失败会报相关错误
        subject.login(token);
        System.out.println("用户认证状态"+subject.isAuthenticated());
        //7.检查授权资源
        System.out.println("是否拥有admin角色"+subject.hasRole("admin"));
        System.out.println("是否拥有public角色"+subject.hasRole("public"));
        //8.检查是否有某个权限
        System.out.println(subject.isPermitted("product:create"));

        //获取主体信息
        System.out.println("用户名"+subject.getPrincipal());

        //退出
        subject.logout();
        System.out.println("用户认证状态"+subject.isAuthenticated());
    }

    @Test
    public void test02(){
        Subject subject = ShiroUtil.login("admin", "123456");

        System.out.println("login后用户认证状态"+subject.isAuthenticated());
        //7.检查授权资源
        System.out.println("是否拥有系统管理员角色"+subject.hasRole("系统管理员"));
        System.out.println("是否拥有系统运维角色"+subject.hasRole("系统运维"));
        System.out.println("是否拥有超级管理员"+subject.hasRole("超级管理员"));
        //8.检查是否有某个权限
        System.out.println("user:list"+subject.isPermitted("user:list"));

        //获取主体信息
        System.out.println("用户名"+subject.getPrincipal());

        //退出
        subject.logout();
        System.out.println("logout后用户认证状态"+subject.isAuthenticated());
    }

    @Test
    public void test03(){
        String md5pwd = new Md5Hash("123","salt",1024).toString();
        System.out.println(md5pwd);
    }

    @Test
    public void test04(){
        //模拟前端获取的明文密码
        String username = "admin";
        String password = "123456";
        //将明文的密码加密为密文
        String encryptPassword = MD5Util.md5(password,"salt");
        //这个时候的认证方法中的密码也要是加密后的，也就是数据库中的密码是加密后的
        Subject subject = ShiroUtil.login(username, encryptPassword);


    }
}