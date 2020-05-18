package cc.kevinlu.springboot.mybatisxml.service;


import cc.kevinlu.springboot.mybatisxml.entity.User;
import com.example.springboot.mybatisxml.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAllUsers();
}