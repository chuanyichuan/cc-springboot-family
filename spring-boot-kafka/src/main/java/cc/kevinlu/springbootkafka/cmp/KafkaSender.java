package cc.kevinlu.springbootkafka.cmp;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cc.kevinlu.springbootkafka.entity.Message;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("========send: {}", message);
        kafkaTemplate.send("my_topics", JSONObject.toJSONString(message));
    }

}
