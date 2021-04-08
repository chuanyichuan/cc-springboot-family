package cc.kevinlu.ccspringbootrabbitmq.receiver;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chuan
 */
@Component
public class MessageReceiver {

    @RabbitListener(queues = "test_queue_1")
    public void receiverMessage(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接收时间:" + sdf.format(new Date()));
        System.out.println("接收到的消息:" + message);
    }

}
