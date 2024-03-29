package cc.kevinlu.springbootmybatis.generator.dao;

import cc.kevinlu.springbootmybatis.generator.entity.UserRole;
import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}