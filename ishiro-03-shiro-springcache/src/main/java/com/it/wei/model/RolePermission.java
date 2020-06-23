package com.it.wei.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:06
 * @description:
 */
@Data
public class RolePermission implements Serializable {

    private Integer id;
    private Long roleId;
    private Long permissionId;
}