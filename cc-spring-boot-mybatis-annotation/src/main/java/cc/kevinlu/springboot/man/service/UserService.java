package cc.kevinlu.springboot.man.service;

import java.util.List;

import cc.kevinlu.springboot.man.entity.User;

public interface UserService {
    List<User> queryAllUsers();

    /**
     * 增加用户
     *
     */
    int add(User user);
}
