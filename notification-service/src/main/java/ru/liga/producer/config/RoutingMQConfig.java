package ru.liga.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingMQConfig {
    @Bean
    public Declarables myQueue() {
        Queue queueDirectFirst = new Queue("findFreeCourier", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("courier.free"));
    }

    @Bean
    public Declarables myQueueFanout() {
        Queue queueTopicFirst = new Queue("findFreeCourier", false);
        TopicExchange topicExchange = new TopicExchange("topicExchange");

        return new Declarables(queueTopicFirst, topicExchange,
                BindingBuilder.bind(queueTopicFirst).to(topicExchange).with("*.free"));
    }
}
