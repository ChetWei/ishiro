package com.it.wei.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:03
 * @description:
 */
@Data
public class Role implements Serializable {

    private Integer id;
    private String role;
    private String description;
    private Integer available;

}