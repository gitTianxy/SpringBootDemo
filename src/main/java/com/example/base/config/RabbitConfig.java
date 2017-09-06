package com.example.base.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * I. direct mode:
 *  1. queue-name equals to routing key
 *  2. queue bounded to default exchange
 * II. topic mode:
 *  1. queue-name seperate from routing key
 *  2. queue bounded to a topic-exchange
 * III. fanout mode:
 *  1. queue bounded to a fanout-exchange
 */
@Configuration
public class RabbitConfig {
    public final static String HELLO_QUEUE_ROUTINGKEY = "hello";

    public final static String TOPIC_EXCHANGE = "topic_exchange";
    public final static String TOPIC_QUEUE = "topic";
    public final static String TOPIC_ROUTINGKEY = "topic.time";
    public final static String TOPICS_QUEUE = "topics";
    public final static String TOPICS_ROUTINGKEY = "topic.#";

    public final static String FANOUT_EXCHANGE = "fanout_exchange";
    public final static String FANOUT_QUEUE_A = "fanout_queue_a";
    public final static String FANOUT_QUEUE_B = "fanout_queue_b";
    public final static String FANOUT_QUEUE_C = "fanout_queue_c";
    public final static String FANOUT_QUEUE_D = "fanout_queue_d";

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

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Queue fanoutQueueA() {
        return new Queue(FANOUT_QUEUE_A);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue(FANOUT_QUEUE_B);
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue(FANOUT_QUEUE_C);
    }

    @Bean
    public Queue fanoutQueueD() {
        return new Queue(FANOUT_QUEUE_D);
    }

    @Bean
    Binding bindFQA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    @Bean
    Binding bindFQB(Queue fanoutQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    @Bean
    Binding bindFQC(Queue fanoutQueueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }

    @Bean
    Binding bindFQD(Queue fanoutQueueD, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueD).to(fanoutExchange);
    }
}
