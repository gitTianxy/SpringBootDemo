package com.example.application;

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
    public void hello() throws Exception {
        sender.send("hello");
    }
}
