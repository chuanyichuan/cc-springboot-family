package cc.kevinlu.springbootkafka.cmp;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaReceiver {

    //    @KafkaListener(topics = { "my_topics" })
    public void listen(ConsumerRecord consumerRecord) {
        Optional<Object> op = Optional.ofNullable(consumerRecord.value());
        if (op.isPresent()) {
            String msg = (String) op.get();
            log.info("========record: {}", consumerRecord);
            log.info("========message: {}", msg);

        }
    }

    public static void main(String[] args) {
        KafkaReceiver kr = new KafkaReceiver();
        for (int i = 0; i < 100; i++) {
            System.out.println((kr.nextValue("cc_test") & 0x7fffffff) % 100);
        }
    }

    private final ConcurrentMap<String, AtomicInteger> topicCounterMap = new ConcurrentHashMap<>();

    private int nextValue(String topic) {
        AtomicInteger counter = topicCounterMap.get(topic);
        if (null == counter) {
            counter = new AtomicInteger(ThreadLocalRandom.current().nextInt());
            AtomicInteger currentCounter = topicCounterMap.putIfAbsent(topic, counter);
            if (currentCounter != null) {
                counter = currentCounter;
            }
        }
        return counter.getAndIncrement();
    }

}
