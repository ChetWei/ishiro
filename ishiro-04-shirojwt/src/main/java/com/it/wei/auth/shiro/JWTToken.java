package com.it.wei.auth.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: weimingan
 * @Date: 2020/6/23 15:10
 * @Description:
 */
@Data
@AllArgsConstructor
public class JWTToken implements AuthenticationToken {

    private String jwtToken;

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }

    //不返回凭证
    @Override
    public Object getCredentials() {
        return null;
    }
}