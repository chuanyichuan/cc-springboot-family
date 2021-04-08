package cc.kevinlu.sbm.generator.dao;

import java.util.List;

import cc.kevinlu.sbm.generator.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
