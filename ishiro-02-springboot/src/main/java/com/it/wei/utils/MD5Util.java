package com.it.wei.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:25
 * @description:
 */
public class MD5Util {

    /**
     * 根据明文和盐生成加密密码
     * @param password
     * @param salt
     * @return
     */
    public static String md5(String password,String salt){
        return new Md5Hash(password,salt,1024).toString();
    }
}