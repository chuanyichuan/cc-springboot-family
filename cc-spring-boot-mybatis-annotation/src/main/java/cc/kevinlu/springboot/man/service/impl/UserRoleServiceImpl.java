package cc.kevinlu.springboot.man.service.impl;

import cc.kevinlu.springboot.man.dao.mapper.UserRoleMapper;
import com.example.springboot.mybatisannotation.entity.User;
import cc.kevinlu.springboot.man.entity.UserRole;
import cc.kevinlu.springboot.man.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;



    /**
     * 增加用户角色
     */
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public int add(UserRole userRole) {
        return userRoleMapper.add(userRole);
    }
}
