package cc.kevinlu.springboot.mybatisxml.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.kevinlu.springboot.mybatisxml.dao.mapper.UserMapper;
import cc.kevinlu.springboot.mybatisxml.entity.User;
import cc.kevinlu.springboot.mybatisxml.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUsers() {
        log.info("/queryAllUsers start...");
        return userMapper.queryAllUsers();
    }
}
