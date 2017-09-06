package com.example.message;

import com.example.base.config.RabbitConfig;
import com.example.utils.StringUtil;
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

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_A)
    public void listenFQA(Message message) {
        logger.info("***Fanout-queue-A listener: {}", StringUtil.bytes2String(message.getBody(), message.getMessageProperties().getContentEncoding()));
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_B)
    public void listenFQB(Message message) {
        logger.info("***Fanout-queue-B listener: {}", StringUtil.bytes2String(message.getBody(), message.getMessageProperties().getContentEncoding()));
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_C)
    public void listenFQC(Message message) {
        logger.info("***Fanout-queue-C listener: {}", StringUtil.bytes2String(message.getBody(), message.getMessageProperties().getContentEncoding()));
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_D)
    public void listenFQD(Message message) {
        logger.info("***Fanout-queue-D listener: {}", StringUtil.bytes2String(message.getBody(), message.getMessageProperties().getContentEncoding()));
    }
}
