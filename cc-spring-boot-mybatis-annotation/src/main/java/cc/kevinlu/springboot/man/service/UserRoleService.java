package cc.kevinlu.springboot.man.service;


import cc.kevinlu.springboot.man.entity.UserRole;

public interface UserRoleService {

    /**
     * 增加用户角色
     */
    int add(UserRole userRole);
}