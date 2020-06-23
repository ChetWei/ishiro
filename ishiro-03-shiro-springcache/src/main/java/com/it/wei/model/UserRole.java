package com.it.wei.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:05
 * @description:
 */
@Data
public class UserRole implements Serializable {
    private Integer id;
    private Long userId;
    private Long roleId;
}