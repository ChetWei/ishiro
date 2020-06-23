package com.it.wei.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:02
 * @description:
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String salt;
}