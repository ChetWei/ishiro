package com.it.wei.config.shiro;

import com.it.wei.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 16:26
 * @description:
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 授权
     * 将认证通过的用户的角色和权限信息设置到对应用户的主体上
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //这里的主身份信息就是验证的时候传入的 主体
        String username = principals.getPrimaryPrincipal().toString();
        //模拟从数据库获取当前用户的角色和权限
        Set<String> roleNameSet = new HashSet<String>();
        roleNameSet.add("系统管理员");
        roleNameSet.add("系统运维");

        Set<String> permissionNameSet = new HashSet<String>();
        permissionNameSet.add("user:list");
        permissionNameSet.add("user:info");
        permissionNameSet.add("user:create");
        permissionNameSet.add("user:update");

        //简单授权信息对象，将角色和权限信息设置进去
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleNameSet);
        simpleAuthorizationInfo.addStringPermissions(permissionNameSet);
        System.out.println("授权成功...");
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //多态强转回子类
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        //根据用户名去db查询对应的用户信息，进行校验
        User user = new User("admin", "123456");
        if(!user.getUsername().equals(username)){
            throw new UnknownAccountException("用户名不存在");
        }
        if(!user.getPassword().equals(password)){
            throw new CredentialsException("密码错误");
        }

        System.out.println("登录认证成功！");

        //创建简单认证信息对象
        SimpleAuthenticationInfo simpleAuthenticationInfo =  new SimpleAuthenticationInfo(
                //传入主体，凭证，父类中的获取realm名称
            token.getPrincipal(),token.getCredentials(),getName()
        );

        return simpleAuthenticationInfo;
    }
}