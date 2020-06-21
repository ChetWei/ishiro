package com.it.wei.test;

import com.it.wei.ShiroSpringbootApp;
import com.it.wei.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:28
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShiroSpringbootApp.class})
public class Test01 {

    @Autowired
    private DefaultWebSecurityManager securityManager;

    @Test
    public void test01(){
        String encrypt = MD5Util.md5("123", "b208a738f7b1426b8e391e2ada28be74");
        System.out.println(encrypt);
    }

    @Test
    public void test02(){
        //获得一个subject实例
        Subject subject = SecurityUtils.getSubject();
        //创建用于认证的token，记录用户认证的账号密码
        AuthenticationToken token = new UsernamePasswordToken("admin","123");
        //主体进行登录，登录的时候进行认证检查,如果失败会报相关错误
        subject.login(token);
    }
}