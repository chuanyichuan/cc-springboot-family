package cc.kevinlu.springcloudeurekahealth.components;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import cc.kevinlu.springcloudeurekahealth.controller.HealthPolicyBean;

@Component
public class CcHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (HealthPolicyBean.dbHealth && HealthPolicyBean.redisHealth && HealthPolicyBean.mongoHealth) {
            return new Health.Builder(Status.UP).build();
        } else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
