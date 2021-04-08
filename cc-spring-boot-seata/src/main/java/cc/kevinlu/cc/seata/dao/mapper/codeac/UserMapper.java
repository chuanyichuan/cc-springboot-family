package cc.kevinlu.cc.seata.dao.mapper.codeac;

import java.util.List;

import cc.kevinlu.cc.seata.dao.model.source2.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
