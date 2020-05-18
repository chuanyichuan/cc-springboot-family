package cc.kevinlu.springbootkafka.partitions;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CcPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (log.isDebugEnabled()) {
            log.debug("{}------------{}", topic, cluster.availablePartitionsForTopic(topic).size());
        }
        return 0;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
