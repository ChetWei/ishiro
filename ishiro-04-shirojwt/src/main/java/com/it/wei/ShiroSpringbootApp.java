package com.it.wei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:18
 * @description:
 */
@SpringBootApplication
@MapperScan("com.it.wei.mapper")
public class ShiroSpringbootApp {
    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringbootApp.class,args);
    }
}