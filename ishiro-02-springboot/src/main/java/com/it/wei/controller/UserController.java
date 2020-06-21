package com.it.wei.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: chetwei@163.com
 * @date: 2020/6/21 22:43
 * @description:
 */
@RestController
public class UserController {

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "success";
    }

    @GetMapping("/add")
    @RequiresPermissions(value = "user:add")
    public String add(){
        return "success";
    }

    @GetMapping("/update")
    @RequiresPermissions(value = "user:update")
    public String update(){
        return "success";
    }
}