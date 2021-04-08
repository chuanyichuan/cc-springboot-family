package cc.kevinlu.sbm.generator.service;

import java.util.List;

import cc.kevinlu.sbm.generator.entity.User;

public interface UserService {
    List<User> queryAllUsers();

    /**
     * 增加用户
     *
     */
    int add(User user);
}
