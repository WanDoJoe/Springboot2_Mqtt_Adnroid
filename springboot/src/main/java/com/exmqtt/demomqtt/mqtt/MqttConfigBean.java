package com.exmqtt.demomqtt.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfigBean {

    String host;//127.0.0.1:1883
    String clientinid;
    String clientoutid;
    String topic;
    String qoslevel;
//            #MQTT 认证
    String username;
    String password;
//    # 10s
    String timeout;
//            #20s
    String  keepalive;
}
