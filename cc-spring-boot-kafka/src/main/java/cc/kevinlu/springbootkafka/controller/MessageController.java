package cc.kevinlu.springbootkafka.controller;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.springbootkafka.cmp.KafkaSender;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class MessageController {

    @Resource
    private KafkaSender         kafkaSender;

    private final static Marker KAFKA_MARKER       = MarkerManager.getMarker("Kafka");
    private final static Marker KAFKA_TRACK_MARKER = MarkerManager.getMarker("Track");

    @GetMapping("/sd/{msg}")
    public String send(@PathVariable(name = "msg") String msg) {
        log.info("send sync message!");
        kafkaSender.send(msg);
        return "success";
    }

    @GetMapping("/log")
    public String sendLog() {
        log.info(KAFKA_TRACK_MARKER, "send async message!");
        for (int i = 0; i < 10; i++) {
            log.info(KAFKA_MARKER, "kafka log i = {}", i);
        }
        return "success";
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("acks", "all");
        prop.put("retries", "0");
        prop.put("batch.size", "1");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
        for (int i = 0; i < 101; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("my_topics", 1, i + "", "value_" + i);
            producer.send(record);
        }
        producer.flush();
        //        producer.close();
        // producer.close(Duration.ofMillis(1000));
        // Thread.sleep(10000);
    }

}
