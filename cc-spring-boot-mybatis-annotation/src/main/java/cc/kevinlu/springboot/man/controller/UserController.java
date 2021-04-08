package cc.kevinlu.springboot.man.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.springboot.man.entity.User;
import cc.kevinlu.springboot.man.service.UserService;
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
    private UserService userService;

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
}
