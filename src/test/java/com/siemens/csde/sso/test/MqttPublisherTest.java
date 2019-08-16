package com.siemens.csde.sso.test;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class MqttPublisherTest {
    public static final String APOLLO_USER="admin";
    public static final  String APOLLO_PASSWORD="password";
    public static final String APOLLO_HOST="tcp://192.168.1.47:61613";
    public static final String PUBLISHER_CLIENT_ID="publisher";
    public static final String APOLLO_TOPIC="/topic/iot";
    @Test
    public void publish() throws Exception{
        MqttClient client=new MqttClient(APOLLO_HOST, PUBLISHER_CLIENT_ID, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(APOLLO_USER);
        options.setPassword(APOLLO_PASSWORD.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        client.setCallback(new PushCallback());
        client.connect(options);
        MqttTopic topic = client.getTopic(APOLLO_TOPIC);
        MqttMessage message = new MqttMessage();
        message.setQos(2);
        message.setRetained(true);
        message.setPayload("abc1".getBytes());
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();

        message.setPayload("abc1".getBytes());
        token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "+ token.isComplete());
        client.disconnect();


    }

}