package cc.kevinlu.ccs.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClusterConfigTest {

    @Resource
    private RedisTemplate<String, Object> template;

    @Test
    public void test() {
        template.opsForValue().set("quyesdjhb", "wqwe");
        Object a = template.opsForValue().get("quyesdjhb");
        System.out.println(a);
    }

}
