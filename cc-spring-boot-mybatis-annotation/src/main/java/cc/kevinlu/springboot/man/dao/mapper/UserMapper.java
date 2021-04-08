package cc.kevinlu.springboot.man.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import cc.kevinlu.springboot.man.entity.User;

@Mapper
public interface UserMapper {

    /*
    * 查询 所有用户
    */
    //    @Select("SELECT * FROM user ")
    List<User> queryAllUsers();

    /*
     * 新增数据，并把主键绑定到User实体类
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into User(name,password) values (#{name},#{password})")
    int add(User user);
}
