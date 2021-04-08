package cc.kevinlu.springboot.mybatisxml.dao.mapper;

import java.util.List;

import cc.kevinlu.springboot.mybatisxml.entity.User;

public interface UserMapper {

    List<User> queryAllUsers();
}
