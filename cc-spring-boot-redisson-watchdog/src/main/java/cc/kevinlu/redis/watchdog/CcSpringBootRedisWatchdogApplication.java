package cc.kevinlu.redis.watchdog;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;

@SpringBootApplication
@RestController
public class CcSpringBootRedisWatchdogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcSpringBootRedisWatchdogApplication.class, args);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://");
        config.setLockWatchdogTimeout(10);
        return Redisson.create(config);
    }

    @Resource
    private Redisson redisson;

    @SneakyThrows
    @GetMapping("/test")
    public void test() {
        RLock lock = redisson.getLock("my_lock");
        if (lock.tryLock(5L, 15L, TimeUnit.SECONDS)) {
            System.out.println("lock success!");
        } else {
            System.out.println("lock error!");
        }
        Thread.sleep(16L);
        lock.unlock();
    }

}
