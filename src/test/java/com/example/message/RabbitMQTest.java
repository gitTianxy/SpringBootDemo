package com.example.message;

import com.example.base.config.RabbitConfig;
import com.example.message.MsgSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private MsgSender sender;

    @Test
    public void testHello() throws Exception {
        for (int i = 0; i < 10; i++) {
            sender.send(RabbitConfig.HELLO_QUEUE_ROUTINGKEY, "hello");
        }
    }

    @Test
    public void testTopic() {
        for (int i = 0; i < 20; i++) {
            sender.sendTopic(RabbitConfig.TOPIC_EXCHANGE, RabbitConfig.TOPIC_ROUTINGKEY, "topic" + i);
        }
    }

    @Test
    public void testTopics() {
        for (int i = 0; i < 20; i++) {
            sender.sendTopics(RabbitConfig.TOPIC_EXCHANGE, "topic." + i, "topic" + i);
        }
    }

    @Test
    public void testFanout() {
        for (int i = 0; i < 10; i++) {
            sender.sendFanout(RabbitConfig.FANOUT_EXCHANGE, "fanout_" + i);
        }
    }
}
