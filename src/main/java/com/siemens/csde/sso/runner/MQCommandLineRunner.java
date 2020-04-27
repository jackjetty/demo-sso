package com.siemens.csde.sso.runner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.siemens.csde.sso.config.amqp.AmqpConfig;
import com.siemens.csde.sso.jpa.repository.RoleRepository;
import com.siemens.csde.sso.pojo.no.FPYNo;
import com.siemens.csde.sso.pojo.no.FPYNo.FPYSubNo;
import com.siemens.csde.sso.pojo.no.OutputNo;
import com.siemens.csde.sso.pojo.no.OutputNo.OutputSubNo;
import com.siemens.csde.sso.pojo.no.WIPNo;
import com.siemens.csde.sso.pojo.no.WIPNo.WIPSubNo;
import com.siemens.csde.sso.service.UserService;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
@Component
@Slf4j
public class MQCommandLineRunner implements CommandLineRunner {

    @Autowired
    private Gson gson;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionFactory connectionFactory;

    //@Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private ContextRefresher contextRefresher;

    /*@Autowired
    @Qualifier("testQueue")
    private Queue testQueue;*/


    @Override
    //@Transactional
    public void run(String... args) throws Exception {
        //connectionFactory.createConnection().
        //connectionFactory.addConnectionListener();
        //TimeUnit.SECONDS.sleep(20);
        rabbitAdmin=new RabbitAdmin(connectionFactory);
        //Queue queue = new Queue("macb.test001", true);
        //rabbitAdmin.declareQueue(queue);
        //log.info("connection is :{} ,connectionFactory:{}",rabbitAdmin==null,connectionFactory==null);
        //1、获取channel对象，控制队列和路由和交换机
       // Channel channel = connectionFactory.newConnection()
        //2、定义队列
        /**
         * queue：队列名称
         * durable：是否持久化，true：当消息队列重新启动后，队列还存在
         *                         false：当队列重启后，队列不存在。
         * exclusive：独有的
         *             当前的消息队列是否由生产者独占，如果配置为true表示消费者不能
         * autoDelete：是否自动删除。如果为true，则消息队列中没有消息时，该队列自动删除。、
         * arguments：提交的参数。一般为null
         */
        //channel.queueDeclare("queue_simple", false, false, false, null);

      /*  for(int i=40;i<60;i++){
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setCode("R"+i);
            roleEntity.setName("系统管理员");
            roleRepository.save(roleEntity);
        }
*/
       // userService.testUsers();

      /*for(int i=1;i<5;i++){
            new MyThread(rabbitTemplate,i).start();

        }*/


        //动态声明队列


        //userRepository.deleteAll("U3");
        Timer timer = new Timer();
       // timer.schedule(new MyTimerTask(rabbitTemplate)  , 450,2);



        SecureRandom random = new SecureRandom();
       /* for(int i=1;i<=100;i++){
            OutputSubNo outputSubNo = OutputSubNo.builder().output(i).productId("product-001").productName("产品1").orderId("")
                    .time(Instant.now().toString()).changeOver(false).build();
            OutputNo outputNo = new OutputNo();
            outputNo.setAssetId("2c9083b063007b44016426d2a8c0001e");
            outputNo.setLineId("3b1c0a00a76c11e8b7856f1df029bd20");
            outputNo.setLineName("App@3RW51_Test");
            outputNo.setTimeseries(Lists.newArrayList(outputSubNo));
            //发送
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_DIRECT_MACB,"routingkey.consumer.0",gson.toJson(outputNo));
            TimeUnit.SECONDS.sleep(random.nextInt(20));
        }*/

        /*for(int i=1;i<=1000;i++){
            WIPSubNo wipSubNo = WIPSubNo.builder().wip(random.nextInt(100)).productId("product-001").productName("产品1").orderId("")
                    .time(Instant.now().toString()).changeOver(false).build();
            WIPNo wipNo = new WIPNo();
            wipNo.setAssetId("2c9083b063007b44016426d2a8c0001e");
            wipNo.setLineId("3b1c0a00a76c11e8b7856f1df029bd20");
            wipNo.setLineName("App@3RW51_Test");
            wipNo.setTimeseries(Lists.newArrayList(wipSubNo));
            //发送
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_DIRECT_MACB,"routingkey.consumer.0",gson.toJson(wipNo));
            //TimeUnit.SECONDS.sleep(random.nextInt(20));
        }*/


        for(int i=1;i<=1000;i++){
            FPYSubNo fpySubNo = FPYSubNo.builder().fpy(random.nextInt(100)).defect(random.nextInt(100)).total(100).productId("product-001").productName("产品1").orderId("")
                    .time(Instant.now().toString()).changeOver(false).build();
            FPYNo fpyNo = new FPYNo();
            fpyNo.setAssetId("2c9083b063007b44016426d2a8c0001e");
            fpyNo.setLineId("3b1c0a00a76c11e8b7856f1df029bd20");
            fpyNo.setLineName("App@3RW51_Test");
            fpyNo.setTimeseries(Lists.newArrayList(fpySubNo));
            //发送
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_DIRECT_MACB,"routingkey.consumer.0",gson.toJson(fpyNo));
            //TimeUnit.SECONDS.sleep(random.nextInt(20));
        }


    }
    class MyThread extends Thread{
        private RabbitTemplate rabbitTemplate;

        private SecureRandom random = new SecureRandom();
        private int id;
        public MyThread(RabbitTemplate rabbitTemplate,int id){
            this.rabbitTemplate=rabbitTemplate;
            this.id=id;
        }
        @Override
        public void run() {
            String productId="productId-"+id ;

            for(int i=0;i<100;i++){
                OutputSubNo outputSubNo = OutputSubNo.builder().output(i).productId(productId).orderId("")
                        .time(Instant.now().toString()).changeOver(false).build();
                OutputNo outputNo = new OutputNo();
                outputNo.setTimeseries(Lists.newArrayList(outputSubNo));
                //synchronized (rabbitTemplate){
                    rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME, "topic.message.test",  outputNo);
               // }

            }

        }
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
            //rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME, "topic.message.test", outputNo);

            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_DIRECT_MACB, "routingkey.direct."+output%2,  outputNo);
        }

    }
}