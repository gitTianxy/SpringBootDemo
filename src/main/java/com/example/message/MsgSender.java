package com.example.message;

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

    public void send(String message) {
        logger.info("Sender: " + message);
        this.rabbitTemplate.convertAndSend("hello", message);
    }
}
