package com.it.wei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.it.wei.mapper.UserMapper;
import com.it.wei.model.User;
import com.it.wei.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 20:21
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserAccount(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}