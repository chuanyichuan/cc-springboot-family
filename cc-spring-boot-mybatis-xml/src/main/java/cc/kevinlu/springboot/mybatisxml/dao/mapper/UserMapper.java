package cc.kevinlu.springboot.mybatisxml.dao.mapper;


import cc.kevinlu.springboot.mybatisxml.entity.User;
import com.example.springboot.mybatisxml.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> queryAllUsers();
}