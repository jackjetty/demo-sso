package com.siemens.csde.sso.component;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.siemens.csde.sso.pojo.no.OutputNo;
import com.siemens.csde.sso.pojo.no.OutputNo.OutputSubNo;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.siemens.csde.sso.config.amqp.AmqpConfig;


//@RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "queue.ff.ff.sendPush"), exchange = @Exchange(value = AmqpConfig.EXCHANGE_DIRECT_MACB,durable="true"), key = "routingkey.direct.1")})
//@Component
//@RabbitListener(queues= "queue.direct.group0")
@RefreshScope
@Slf4j
public class DirectReceiver {

    @Autowired
    private Gson gson;

    @RabbitHandler
    public void process(@Payload OutputNo outputNo,@Header(AmqpHeaders.CONSUMER_QUEUE) String queue,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        //log.info("topic1 receiver:{}",data);
        log.info("topic1 receiver:{}",gson.toJson(outputNo));
        //@Payload String data,

       /* try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}