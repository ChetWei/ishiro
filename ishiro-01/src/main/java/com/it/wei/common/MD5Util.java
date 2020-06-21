package com.it.wei.common;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 17:34
 * @description:
 */
public class MD5Util {

    public static String md5(String password,String salt){
        return new Md5Hash(password,salt).toString();

    }
}