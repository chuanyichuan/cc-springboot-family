package cc.kevinlu.shardingjdbc.config;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

public class DemoTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        for (String targetName : availableTargetNames) {
            if (targetName.endsWith(shardingValue.getValue() % 2 + "")) {
                return targetName;
            }
        }
        throw new IllegalArgumentException();
    }
}
