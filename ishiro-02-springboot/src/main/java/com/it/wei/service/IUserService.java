package com.it.wei.service;

import com.it.wei.model.User;

public interface IUserService {
    //根据用户账户名称查询用户
    User findUserByUserAccount(String username);
}
