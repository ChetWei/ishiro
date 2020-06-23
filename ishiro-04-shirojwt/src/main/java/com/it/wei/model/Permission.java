package com.it.wei.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:04
 * @description:
 */
@Data
public class Permission implements Serializable {

    private Integer id;
    private String name;
    private String permission;
    private String url;

}