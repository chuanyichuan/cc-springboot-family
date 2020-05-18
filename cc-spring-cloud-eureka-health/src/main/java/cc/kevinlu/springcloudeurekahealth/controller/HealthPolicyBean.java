package cc.kevinlu.springcloudeurekahealth.controller;

import java.util.List;
import java.util.concurrent.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.netflix.discovery.TimedSupervisorTask;

@Component
public class HealthPolicyBean implements InitializingBean {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    public static boolean                 dbHealth    = true;
    public static boolean                 redisHealth = true;
    public static boolean                 mongoHealth = true;

    @GetMapping("/cc/health")
    public Boolean health() {
        System.out.println("-----start check health-----");
        return dbHealth && redisHealth && mongoHealth;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

        ThreadPoolExecutor heartbeatExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("redis-HeartbeatExecutor-%d").setDaemon(true).build());

        TimedSupervisorTask task = new TimedSupervisorTask("redis-heartbeat", scheduled, heartbeatExecutor, 10,
                TimeUnit.SECONDS, 100, new RedisTimer());

        scheduled.schedule(task, 10, TimeUnit.SECONDS);
    }

    protected class RedisTimer implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("---check redis---");
                List<RedisClientInfo> clientList = redisTemplate.getClientList();
                if (clientList == null || clientList.isEmpty()) {
                    HealthPolicyBean.redisHealth = false;
                } else {
                    HealthPolicyBean.redisHealth = true;
                }
                System.out.println("PolicyController.redisHealth::::" + HealthPolicyBean.redisHealth);
            } catch (Exception e) {
                HealthPolicyBean.redisHealth = false;
            }
        }
    }

}
