package com.siemens.csde.sso;

import ch.qos.logback.classic.LoggerContext;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@ServletComponentScan
@EnableJpaRepositories("com.siemens.csde.sso.jpa.repository")
@EntityScan("com.siemens.csde.sso.jpa.entity")
@ComponentScan(basePackages = {"com.siemens"})
public class SSOApplication extends SpringBootServletInitializer implements CommandLineRunner {

    /*@Bean
    Logger.Level feignLoggerLevel(){
        return Level.FULL;
    }*/

    @Autowired
    private org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SSOApplication.class);
    }

    public static void main(String[] args) {
        LoggerContext loggerContext =(LoggerContext)LoggerFactory.getILoggerFactory();
        loggerContext.putProperty("APP_NAME","new");
        SpringApplication.run(SSOApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        int count=0;
        while(true){
            //log.info("test log dynimic :{},context:{}", Optional.ofNullable(loggerContext.getProperty("APP_NAME")).orElse("unknown"), Optional.ofNullable(loggerContext.getProperty("CONTEXT_NAME")).orElse("unknown"));
            log.info("test dynimic log");
            TimeUnit.SECONDS.sleep(1);
            if(count>20){
                break;
            }
        }


       /* ConnectionFactory factory = new ConnectionFactory();
        //设置服务器地址
        factory.setHost("192.168.2.2");
        //设置端口号
        factory.setPort(5672);
        //设置vhost
        factory.setVirtualHost("/");
        factory.setConnectionTimeout(10000);
        factory.setUsername("admin");
        factory.setPassword("admin");*/


        ConnectionFactory factory=((CachingConnectionFactory)connectionFactory).getRabbitConnectionFactory();
        factory.setAutomaticRecoveryEnabled(true);

        //获取一个连接
        Connection connection = factory.newConnection();
        //获取一个通道
        Channel channel = connection.createChannel();
        //定义队列的消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body,"utf-8");
                log.info(msg);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //监听队列
        channel.basicConsume("queue.direct.group0",true,defaultConsumer);

      /*  TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();*/
    }
}