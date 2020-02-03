package com.siemens.csde.sso.component;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.siemens.csde.sso.pojo.no.OutputNo;
import com.siemens.csde.sso.pojo.no.OutputNo.OutputSubNo;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.siemens.csde.sso.config.amqp.AmqpConfig;

//@Component
//@RabbitListener(queues = AmqpConfig.MESSAGE_QUEUE)
@Slf4j
public class TopicReceiver {

    @Autowired
    private Gson gson;

    private  static ConcurrentMap<String,Integer> lastOutputMap= Maps.newConcurrentMap();

    @RabbitHandler
    public void process(@Payload OutputNo outputNo,@Header(AmqpHeaders.CONSUMER_QUEUE) String queue,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        //log.info("topic1 receiver:{}",data);

        //@Payload String data,

        String productId=outputNo.getTimeseries().get(0).getProductId();
       int output=outputNo.getTimeseries().get(0).getOutput();
        log.info(" productId:{},output:{}",  productId,output);
       if(!lastOutputMap.containsKey(productId)){
            lastOutputMap.put(productId,output);
        }
        if(output<lastOutputMap.get(productId)){
            log.info("productId:{},last output:{},current output:{}", productId,lastOutputMap.get(productId),output);
        }
        lastOutputMap.put(productId,output);
       /* try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}