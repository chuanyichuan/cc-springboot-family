package cc.kevinlu.ccspringbootrabbitmq.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author chuan
 */
@Service
public class MessageServiceImpl {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String queueName, String message, int delayTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend("test_delayed_exchange", queueName, message, message1 -> {
            message1.getMessageProperties().setHeader("x-delay", delayTime);
            return message1;
        });
    }

}
