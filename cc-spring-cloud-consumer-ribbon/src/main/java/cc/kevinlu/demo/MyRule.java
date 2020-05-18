package cc.kevinlu.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@Configuration
public class MyRule {

    @Bean
    public IRule ribbonRule() {
        WeightedResponseTimeRule rule = new WeightedResponseTimeRule();
        IClientConfig config = new DefaultClientConfigImpl();
        config.loadDefaultValues();
        config.set(WeightedResponseTimeRule.WEIGHT_TASK_TIMER_INTERVAL_CONFIG_KEY, 5 * 1000);
        rule.initWithNiwsConfig(config);
        return rule;
    }

}
