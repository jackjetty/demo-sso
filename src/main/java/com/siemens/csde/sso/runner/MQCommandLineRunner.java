package com.siemens.csde.sso.runner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.siemens.csde.sso.config.amqp.AmqpConfig;
import com.siemens.csde.sso.jpa.entity.RoleEntity;
import com.siemens.csde.sso.jpa.repository.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        for(int i=40;i<60;i++){
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setCode("R"+i);
            roleEntity.setName("系统管理员");
            roleRepository.save(roleEntity);
        }


        //Timer timer = new Timer();
        //timer.schedule(new MyTimerTask(rabbitTemplate)  , 450,2);

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
            int output=random.nextInt(99);
            OutputSubNo outputSubNo = OutputSubNo.builder().output(output).productId("productId-001").orderId("TP-Order-Id-001")
                    .time(now.toString()).changeOver(false).build();
            OutputNo outputNo = new OutputNo();
            outputNo.setTimeseries(Lists.newArrayList(outputSubNo));
             json = gson.toJson(outputNo);
            //log.info("mq:{}", json);
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME, "topic.message.test", outputNo);

            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_DIRECT_MACB, "routingkey.direct."+output%2,  outputNo);
        }

    }
}