package com.siemens.csde.sso.config.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.siemens.csde.sso.component.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.Connection;
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
    public static final String EXCHANGE_DIRECT_MACB = "exchange.direct.macb";




    @Bean
    @Qualifier(MESSAGE_QUEUE)
    Queue queueMessage( ) {
        Queue queue = new Queue(MESSAGE_QUEUE, true);
        return queue;
    }

    @Bean
    Queue directQueue0( ) {
        Queue queue = new Queue("queue.direct.group0", false);
        return queue;
    }

    @Bean
    Queue directQueue1( ) {
        Queue queue = new Queue("queue.direct.group1", false);
        return queue;
    }



    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_DIRECT_MACB);
    }

    @Bean
    Binding binding0(DirectExchange directExchange,   Queue directQueue0) {
        Binding binding = BindingBuilder.bind(directQueue0).to(directExchange).with("routingkey.direct.0");
        return binding;
    }


    @Bean
    Binding consumerBinding(DirectExchange directExchange) {
        Binding binding = BindingBuilder.bind(new Queue("queue.macb.provider.group0", true)).to(directExchange).with("routingkey.consumer.0");
        return binding;
    }

    @Bean
    Binding binding1(DirectExchange directExchange,   Queue directQueue1) {
        Binding binding = BindingBuilder.bind(directQueue1).to(directExchange).with("routingkey.direct.1");
        return binding;
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


//            MessageListenerAdapter listenerAdapter
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
    /*    com.rabbitmq.client.ConnectionFactory connectionFactory =
                rabbitTemplate.getConnectionFactory();*/
        //connectionFactory.addConnectionListener();
        // 开启连接 - tcp连接
        //Connection connection = connectionFactory.createConnection();

        // 建立信道 构造参数 true代表该信道开启 Transactional 事务模式, false 代表为非事务模式
        //Channel channel = connection.createChannel(false);



        container.setQueueNames("queue.direct.group1");
        container.setConcurrentConsumers(1);
        container.setConsumerTagStrategy(queue -> "consumer_"+(queue));
        container.setAfterReceivePostProcessors(message -> {
            message.getMessageProperties().getHeaders().put("desc",10);
            return message;
        });
       //container.addQueueNames("queue.direct.group0");
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener((MessageListener) message -> {
                    if("queue.direct.group1".equals(message.getMessageProperties().getConsumerQueue())){
                        //log.info("queue name:{}",message.getMessageProperties().getConsumerQueue());
                        //log.info("properties:{}",message.getMessageProperties());
                        log.info("queue name:{},body:{}",message.getMessageProperties().getConsumerQueue(),new String(message.getBody()));
                    }
                    if("queue.direct.group0".equals(message.getMessageProperties().getConsumerQueue())){
                        //log.info("queue name:{}",message.getMessageProperties().getConsumerQueue());
                        //log.info("properties:{}",message.getMessageProperties());
                        log.info("queue name:{},body:{}",message.getMessageProperties().getConsumerQueue(),new String(message.getBody()));
                    }
        });
        //container.setMessageListener(listenerAdapter);
        return container;
    }

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