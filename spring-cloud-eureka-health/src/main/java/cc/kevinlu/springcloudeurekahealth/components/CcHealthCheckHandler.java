package cc.kevinlu.springcloudeurekahealth.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;

@Component
public class CcHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    private CcHealthIndicator ccHealthIndicator;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus currentStatus) {
        if (ccHealthIndicator.health().getStatus().equals(Status.UP)) {
            return InstanceInfo.InstanceStatus.UP;
        }
        return InstanceInfo.InstanceStatus.DOWN;
    }
}
