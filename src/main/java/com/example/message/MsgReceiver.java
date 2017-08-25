package com.example.message;

import com.example.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class MsgReceiver {
    private final static Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitListener(queues = RabbitConfig.HELLO_QUEUE_ROUTINGKEY)
    public void listenHelloQueue(String message) {
        logger.info("***Hello listener : " + message);
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE)
    public void listenTopicQueue(Message message) {
        logger.info("***Topic listener : " + new String(message.getBody(), Charset.forName(message.getMessageProperties().getContentEncoding())));
    }

    @RabbitListener(queues = RabbitConfig.TOPICS_QUEUE)
    public void listenTopicsQueue(Message message) {
        logger.info("***Topics listener: " + new String(message.getBody(), Charset.forName(message.getMessageProperties().getContentEncoding())));
    }
}
