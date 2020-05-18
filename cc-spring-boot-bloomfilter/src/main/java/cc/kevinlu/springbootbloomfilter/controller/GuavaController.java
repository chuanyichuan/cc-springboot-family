package cc.kevinlu.springbootbloomfilter.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/guava")
public class GuavaController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static BloomFilter<Integer>   bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000000);

    @GetMapping("/search/{id}")
    public Boolean search(@PathVariable(name = "id") String id) {
        //        bloomFilter
        return false;
    }

    @GetMapping("/add/{id}")
    public Boolean add(@PathVariable(name = "id") String id) {
        String script = "return redis.call('bf.add',KEYS[1],ARGV[1])";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        Boolean r = redisTemplate.execute(redisScript, Collections.singletonList("bf2"), "cc" + id);
        return r;
    }

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        Object data = jedis.eval("return redis.call('bf.add',KEYS[1],ARGV[1])", 1, "bf2", "cc" + 123);
        System.out.println(data);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

}
