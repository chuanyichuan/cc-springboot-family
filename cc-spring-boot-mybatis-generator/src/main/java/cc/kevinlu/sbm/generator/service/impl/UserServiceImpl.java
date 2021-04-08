package cc.kevinlu.sbm.generator.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.sbm.generator.dao.UserMapper;
import cc.kevinlu.sbm.generator.entity.User;
import cc.kevinlu.sbm.generator.entity.UserRole;
import cc.kevinlu.sbm.generator.service.UserRoleService;
import cc.kevinlu.sbm.generator.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper      userMapper;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public List<User> queryAllUsers() {
        log.info("/queryAllUsers start...");
        return userMapper.selectAll();
    }

    /**
     * 增加用户
     */
    @Transactional(rollbackFor = Exception.class/*propagation = Propagation.NESTED*/)
    @Override
    public int add(User user) {
        userMapper.insert(user);
        Thread t1 = new Thread(() -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(12);
            userRoleService.add(userRole);
        });
        t1.setUncaughtExceptionHandler((t, s) -> {
            System.out.println("thread occur exception!");
            userMapper.deleteByPrimaryKey(user.getId());
        });
        t1.start();
        return 1;
    }

    // 第一种情况：调用其他service的方法，事务不传递
    //    @Transactional(rollbackFor = Exception.class/*propagation = Propagation.NESTED*/)
    //    @Override
    //    public int add(User user) {
    //        userMapper.insert(user);
    //        Thread t1 = new Thread(() -> {
    //            UserRole userRole = new UserRole();
    //            userRole.setUserId(user.getId());
    //            userRole.setRoleId(12);
    //            userRoleService.add(userRole);
    //        });
    //        t1.start();
    //        try {
    //            t1.join();
    //        } catch (InterruptedException e) {
    //        }
    //        int[] s = new int[1];
    //        System.out.println(s[7]);
    //        return 1;
    //    }
}
