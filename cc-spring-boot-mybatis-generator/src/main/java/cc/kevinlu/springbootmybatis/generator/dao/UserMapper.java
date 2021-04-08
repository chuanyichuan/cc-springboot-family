package cc.kevinlu.springbootmybatis.generator.dao;

import cc.kevinlu.springbootmybatis.generator.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}