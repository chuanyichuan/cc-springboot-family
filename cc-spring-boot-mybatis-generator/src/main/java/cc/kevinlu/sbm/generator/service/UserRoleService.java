package cc.kevinlu.sbm.generator.service;

import java.util.List;

import cc.kevinlu.sbm.generator.entity.UserRole;

public interface UserRoleService {

    /**
     * 增加用户角色
     */
    int add(UserRole userRole);

    List list();
}
