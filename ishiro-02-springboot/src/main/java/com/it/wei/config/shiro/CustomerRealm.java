package com.it.wei.config.shiro;


import com.it.wei.mapper.PermissionMapper;
import com.it.wei.mapper.RoleMapper;
import com.it.wei.model.User;
import com.it.wei.service.IUserService;
import com.it.wei.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 16:26
 * @description:
 */
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private IUserService userService;

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
        User dbUser = userService.findUserByUserAccount(username);
        if(dbUser == null){
            throw new UnknownAccountException("用户不存在");
        }


        //密码校验
        if(! dbUser.getPassword().equals(MD5Util.md5(password, dbUser.getSalt()))){
            throw new CredentialsException("密码错误");
        }

       log.info("登录认证..");

        //创建简单认证信息对象
        SimpleAuthenticationInfo simpleAuthenticationInfo =  new SimpleAuthenticationInfo(
                //传入主体，凭证，父类中的获取realm名称
                dbUser,password,getName()
        );

        return simpleAuthenticationInfo;
    }


    /**
     * 授权
     * 将认证通过的用户的角色和权限信息设置到对应用户的主体上
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //给资源进行进行授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //给当前用户，添加角色和资源的授权字符串,要去数据库查询
        User user = (User) principals.getPrimaryPrincipal(); //获得的就是SimpleAuthenticationInfo 返回的第一个参数
        roleMapper.findRoleByUsername(user.getName()).stream().forEach(
                role -> {
                    //添加角色
                    simpleAuthorizationInfo.addRole(role.getRole());
                    //并且获得每个角色的资源权限
                    permissionMapper.findPermissionByRoleId(role.getId()).stream().forEach(
                            permission -> {
                                simpleAuthorizationInfo.addStringPermission(permission.getPermission()); //设置当前用户的资源权限
                            }
                    );
                }
        );
        log.info("授权完成..");
        return simpleAuthorizationInfo;
    }


}