package cc.kevinlu.demo.service;

import cc.kevinlu.demo.entity.User;

public interface UserService {

    public User getUser(Integer id);

    public void deleteUser(Integer id);

    public User updateUser(User updateUser);
}
