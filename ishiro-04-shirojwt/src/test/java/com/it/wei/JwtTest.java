package com.it.wei;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @author: weimingan
 * @Date: 2020/6/23 14:59
 * @Description:
 */
public class JwtTest {

    //生成jwt
    @Test
    public void test(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("888") //唯一编号
                .setSubject("username") //主题，可以设置用户信息
                .setIssuedAt(new Date()) //签发日期
                .setExpiration(new Date(System.currentTimeMillis() + 1000*10)) //设置超时时间，如果超过了那么解析的时候就会报错
                .signWith(SignatureAlgorithm.HS256, "secret");

        String compact = jwtBuilder.compact();
        System.out.println(compact);

    }

    //解析
    @Test
    public void test02(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTU5Mjg5NTk3MywiZXhwIjoxNTkyODk1OTgzfQ.g3Zp1Bg8XF7CWG_U-wpTQvNPIKGfe382DK98DYZLW4g";

        Claims claims = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims);
    }

}