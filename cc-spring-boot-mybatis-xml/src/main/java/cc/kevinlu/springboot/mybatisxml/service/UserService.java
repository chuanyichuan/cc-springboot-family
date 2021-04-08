package cc.kevinlu.springboot.mybatisxml.service;

import java.util.List;

import cc.kevinlu.springboot.mybatisxml.entity.User;

public interface UserService {
    List<User> queryAllUsers();
}
