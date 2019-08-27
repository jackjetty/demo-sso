package com.siemens.csde.sso.runner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.siemens.csde.sso.config.amqp.AmqpConfig;
import com.siemens.csde.sso.pojo.no.OutputNo;
import com.siemens.csde.sso.pojo.no.OutputNo.OutputSubNo;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MQCommandLineRunner implements CommandLineRunner {

    @Autowired
    private Gson gson;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {

        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(rabbitTemplate)  , 4500,5000);

    }





    class MyTimerTask extends TimerTask {

        private RabbitTemplate rabbitTemplate;
        private SecureRandom random = new SecureRandom();
        private String json;

        public MyTimerTask(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        public void run() {
            Instant now = Instant.now();
            OutputSubNo outputSubNo = OutputSubNo.builder().output(random.nextInt(99)).productId("productId-001").orderId("TP-Order-Id-001")
                    .time(now.toString()).changeOver(false).build();
            OutputNo outputNo = new OutputNo();
            outputNo.setTimeseries(Lists.newArrayList(outputSubNo));
             json = gson.toJson(outputNo);
            //log.info("mq:{}", json);
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME, "topic.message.test", json);
        }

    }
}