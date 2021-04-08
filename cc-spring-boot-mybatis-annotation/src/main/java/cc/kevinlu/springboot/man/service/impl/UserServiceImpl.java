package cc.kevinlu.springboot.man.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.springboot.man.dao.mapper.UserMapper;
import cc.kevinlu.springboot.man.dao.mapper.UserRoleMapper;
import cc.kevinlu.springboot.man.entity.User;
import cc.kevinlu.springboot.man.entity.UserRole;
import cc.kevinlu.springboot.man.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper     userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<User> queryAllUsers() {
        log.info("/queryAllUsers start...");
        return userMapper.queryAllUsers();
    }

    /**
     * 增加用户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int add(User user) {
        userMapper.add(user);
        UserRole userRole = new UserRole();
        userRole.setRoleId(1);
        //            userRole.setUserId(user.getId());
        userRole.setUserId(null);
        userRoleMapper.add(userRole);
        return 1;
    }
}
