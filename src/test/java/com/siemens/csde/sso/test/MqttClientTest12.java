package com.siemens.csde.sso.test;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class MqttClientTest12{
    public final String CLIENT_ID="client12";

    @Test
    public void subscribe() throws Exception{
        MqttClient client=new MqttClient(MqttPublisherTest.APOLLO_HOST, CLIENT_ID, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(MqttPublisherTest.APOLLO_USER);
        options.setPassword(MqttPublisherTest.APOLLO_PASSWORD.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        client.setCallback(new SubscribeCallback(client));
        MqttTopic topic = client.getTopic(MqttPublisherTest.APOLLO_TOPIC);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        options.setWill(topic, "close".getBytes(), 2, true);

        client.connect(options);
        //订阅消息
        int[] Qos  = {1};
        String[] topic1 = {MqttPublisherTest.APOLLO_TOPIC};
        //client.subscribe(topic1, Qos);
        // client.unsubscribe(topic1);
        Thread.sleep(12000000);
    }
}