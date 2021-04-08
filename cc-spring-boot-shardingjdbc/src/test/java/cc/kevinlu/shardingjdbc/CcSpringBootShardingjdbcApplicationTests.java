package cc.kevinlu.shardingjdbc;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cc.kevinlu.shardingjdbc.entity.UserInfo;
import cc.kevinlu.shardingjdbc.mapper.UserInfoMapper;
import cc.kevinlu.shardingjdbc.service.UserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CcSpringBootShardingjdbcApplicationTests {

    @Resource
    private UserInfoMapper  userInfoMapper;
    @Resource
    private UserInfoService userInfoService;

    @Test
    void contextLoads() {

        UserInfo user = userInfoMapper.selectByPrimaryKey(2L);
        System.out.println(user);

    }

    @Test
    void testInsertSelective() {
        userInfoService.insertSelective();
    }

    /**
     * org.apache.shardingsphere.core.exception.ShardingException: Parameter `null` should extends Comparable for sharding value.
     */
    @Test
    void testInsert() {
        userInfoService.insert();
    }

    @Test
    void testInsertConflict() {
        userInfoService.insertConflict();
    }

}
