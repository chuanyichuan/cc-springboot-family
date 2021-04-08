package cc.kevinlu.ccspringbootrabbitmq;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cc.kevinlu.ccspringbootrabbitmq.service.MessageServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class CcSpringBootRabbitmqApplicationTests {

    @Resource
    MessageServiceImpl messageService;

    @Test
    void testSend() {
        for (int i = 0; i < 10; i++) {
            messageService.sendMsg("test_queue_1", "发送消息" + i, i * 1000);
        }
    }

}
