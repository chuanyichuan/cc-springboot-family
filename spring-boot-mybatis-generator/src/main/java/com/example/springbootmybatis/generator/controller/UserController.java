package com.example.springbootmybatis.generator.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmybatis.generator.entity.User;
import com.example.springbootmybatis.generator.service.UserRoleService;
import com.example.springbootmybatis.generator.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * UserController
 *
 * @Author: java_suisui
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService     userService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 查询 所有用户
     */
    @GetMapping("/queryAllUsers")
    public List<User> queryAllUsers() {
        return userService.queryAllUsers();
    }

    @GetMapping("/add")
    public User add(User user) {
        int num = userService.add(user);
        return user;
    }

    @GetMapping("/role")
    public User role(User user) {
        userRoleService.list();
        return user;
    }
}
