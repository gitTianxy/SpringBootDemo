package com.example.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimeReporter {
    private final static Logger logger = LoggerFactory.getLogger(TimeReporter.class);

    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void reportTime() {
        logger.info("!!! current time is: " + new Date());
    }
}
