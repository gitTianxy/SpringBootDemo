package com.example.message;

import com.example.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgSender {
    private final static Logger logger = LoggerFactory.getLogger(MsgSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String routingKey, String message) {
        logger.info("===Sender: " + message);
        this.rabbitTemplate.convertAndSend(routingKey, message);
    }

    public void sendTopic(String exchange, String routineKey, Object message) {
        logger.info("===Topic sender: exchane={}, rk={}, msg={}", exchange, routineKey, message);
        this.rabbitTemplate.convertAndSend(exchange, routineKey, message);
    }

    public void sendTopics(String exchange, String routineKey, Object message) {
        logger.info("===Topics sender: exchane={}, rk={}, msg={}", exchange, routineKey, message);
        this.rabbitTemplate.convertAndSend(exchange, routineKey, message);
    }

    public void sendFanout(String exchange, Object message) {
        logger.info("===Fanout sender: exchange={}, message={}", exchange, message);
        this.rabbitTemplate.convertAndSend(exchange, null, message);
    }
}
