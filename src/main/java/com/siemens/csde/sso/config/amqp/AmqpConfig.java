package com.siemens.csde.sso.config.amqp;

import com.siemens.csde.sso.component.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AmqpConfig {

    /*public static final String TS_QUEUE_NAME = "queue.ts";
    public static final String RESULT_QUEUE_NAME = "queue.result";
    public static final String EXCHANGE_NAME = "mac-exchange";*/

    public static final String MESSAGE_QUEUE = "queue.message";
    public static final String EXCHANGE_NAME = "macb-exchange";


    @Bean
    @Qualifier(MESSAGE_QUEUE)
    Queue queueMessage( ) {
        Queue queue = new Queue(MESSAGE_QUEUE, true);
        return queue;
    }

    @Bean
    @Qualifier(EXCHANGE_NAME)
    TopicExchange topicExchange( ) {
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME);
        return topicExchange;
    }

    @Bean
    Binding binding(@Qualifier(EXCHANGE_NAME) TopicExchange exchange, @Qualifier(MESSAGE_QUEUE) Queue queue) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("topic.message.#");
        return binding;
    }


  /*  @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MESSAGE_QUEUE);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(listenerAdapter);
        return container;
    }*/

    /*@Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
*/

    /*@Bean
    MessageConverter messageConverter() {
         return new Gson2JsonMessageConverter();
        // return new Jackson2JsonMessageConverter();
    }*/
}