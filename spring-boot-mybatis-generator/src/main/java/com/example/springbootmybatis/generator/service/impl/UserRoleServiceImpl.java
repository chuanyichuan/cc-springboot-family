package com.example.springbootmybatis.generator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootmybatis.generator.dao.UserRoleMapper;
import com.example.springbootmybatis.generator.entity.UserRole;
import com.example.springbootmybatis.generator.service.UserRoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 增加用户角色
     */
    @Transactional(rollbackFor = Exception.class/*propagation = Propagation.NESTED*/)
    @Override
    public int add(UserRole userRole) {
        userRoleMapper.insert(userRole);
        int[] s = new int[1];
        System.out.println(s[7]);
        return 0;
    }

    @Override
    public List<UserRole> list() {
        return userRoleMapper.selectAll();
    }
}
