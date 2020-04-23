package com.example.springbootmybatis.generator.service;

import java.util.List;

import com.example.springbootmybatis.generator.entity.User;

public interface UserService {
    List<User> queryAllUsers();

    /**
     * 增加用户
     *
     */
    int add(User user);
}
