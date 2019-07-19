package com.siemens.csde.sso.config.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AmqpConfig {

    public static final String TS_QUEUE_NAME = "queue.ts";
    public static final String RESULT_QUEUE_NAME = "queue.result";
    public static final String EXCHANGE_NAME = "mac-exchange";


    @Bean
    RabbitAdmin localRabbitAdmin(ConnectionFactory connectionFactory) {

        return new RabbitAdmin(connectionFactory);

    }

    @Bean
    Queue queueTs(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(TS_QUEUE_NAME, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueResult(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RESULT_QUEUE_NAME, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME);
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }


    @Bean
    Binding bindingExchangeTs(TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueTs(rabbitAdmin)).to(exchange).with(TS_QUEUE_NAME);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeResult(TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueResult(rabbitAdmin)).to(exchange).with(RESULT_QUEUE_NAME);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
}