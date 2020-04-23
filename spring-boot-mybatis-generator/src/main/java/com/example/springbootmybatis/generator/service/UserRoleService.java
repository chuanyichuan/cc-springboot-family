package com.example.springbootmybatis.generator.service;

import java.util.List;

import com.example.springbootmybatis.generator.entity.UserRole;

public interface UserRoleService {

    /**
     * 增加用户角色
     */
    int add(UserRole userRole);

    List list();
}
