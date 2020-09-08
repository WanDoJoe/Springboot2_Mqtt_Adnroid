package com.exmqtt.demomqtt.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
//@Configuration
//@IntegrationComponentScan
public class MqttConfig  {
    @Autowired
    MqttConfigBean configBean;

    @Bean
    public MqttConnectOptions getMqttConnectOptions(){
        log.info("初始化Mqtt推送服务 配置  MqttConnectOptions");
        log.info("host="+configBean.host);
        log.info("username="+configBean.username);
        log.info("password="+configBean.password);
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(90);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName(configBean.username);
        mqttConnectOptions.setPassword(configBean.password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{configBean.host});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        log.info("初始化Mqtt推送客户端  MqttPahoClientFactory");
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        log.info("注册Mqtt推送服务 MessageHandler mqttOutbound");
        log.info("clientinid="+configBean.clientinid);
//        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(configBean.clientinid, mqttClientFactory());
        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(String.valueOf(System.currentTimeMillis()), mqttClientFactory());
        messageHandler.setAsync(true);

        String topic=configBean.topic;
        log.info("topic="+topic);
        messageHandler.setDefaultTopic(topic);

        log.info("注册Mqtt推送服务 成功");
        return messageHandler;
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        log.info("初始化Mqtt服务  mqttOutboundChannel");
        return new DirectChannel();
    }
}
