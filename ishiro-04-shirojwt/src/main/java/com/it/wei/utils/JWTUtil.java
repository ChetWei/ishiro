package com.it.wei.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.util.Strings;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

/**
 * @author: weimingan
 * @Date: 2020/6/23 15:17
 * @Description:
 */
public class JWTUtil {

    private static final String SECRETKEY = "secret";

    /**
     * 生成一个jwt token
     * @param subject  用户主体 用户的唯一标识
     * @param roles 用户角色名称  {"admin","supperadmin"}
     * @param permissions 权限名称 {"user:add","user:update"}
     * @param expire  过期时间
     * @return
     */
    public static String generatorToken(String subject,String roles,String permissions,Long expire){
        JwtBuilder jwtBuilder = Jwts.builder();
        //设置唯一标识
        jwtBuilder.setId(UUID.randomUUID().toString());
        //设置token主体
        jwtBuilder.setSubject(subject);
        //颁发者
        jwtBuilder.setIssuer("wei");
        //颁发时间
        jwtBuilder.setIssuedAt(new Date());
        if(null != expire){
            //设置有效时间
            Date expiration = new Date(System.currentTimeMillis() + expire);
            jwtBuilder.setExpiration(expiration);
        }
        if(Strings.isNotBlank(roles)) jwtBuilder.claim("roles",roles);
        if(Strings.isNotBlank(permissions)) jwtBuilder.claim("perms",permissions);
        //私钥
        byte[] base64Binary = DatatypeConverter.parseBase64Binary(SECRETKEY);
        //签名
        jwtBuilder.signWith(SignatureAlgorithm.HS256,base64Binary);
        return jwtBuilder.compact();
    }

    /**
     * 解析jwt token
     */
    public static String parseJwtToken(String jwtToken){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                .parseClaimsJws(jwtToken)
                .getBody();


        return null;
    }


}