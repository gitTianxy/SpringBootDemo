package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * I. direct mode:
 *  1. queue-name equals to routing key
 *  2. queue bounded to default exchange
 * II. topic mode:
 *  1. queue-name seperate from routing key
 *  2. queue bounded to a topic exchange
 */
@Configuration
public class RabbitConfig {
    public final static String HELLO_QUEUE_ROUTINGKEY = "hello";

    public final static String TOPIC_QUEUE = "topic";
    public final static String TOPIC_ROUTINGKEY = "topic.time";

    public final static String TOPICS_QUEUE = "topics";
    public final static String TOPICS_ROUTINGKEY = "topic.#";

    public final static String TOPIC_EXCHANGE = "topic_exchange";

    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_QUEUE_ROUTINGKEY);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE);
    }

    @Bean
    public Queue topicsQueue() {
        return new Queue(TOPICS_QUEUE);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindTopic() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPIC_ROUTINGKEY);
    }

    @Bean
    Binding bindTopics() {
        return BindingBuilder.bind(topicsQueue()).to(topicExchange()).with(TOPICS_ROUTINGKEY);
    }
}
