package com.exmqtt.demomqtt.mqttpush;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class MqttPushController {
    public static String TOPIC_LOOP_TEST = "sinosoft/topic";
    @Autowired
    IMQTTPublisher publisher;

    @Autowired
    IMQTTSubscriber subscriber;

    @PostConstruct
    public void init() {
        subscriber.subscribeMessage(TOPIC_LOOP_TEST);
    }
    @RequestMapping(value = "/mqtt/push/test/{msg}")
    public String indexTest(@PathVariable(value = "msg")String msg) {
        publisher.publishMessage("sinosoft/topic", msg);
        return "Success";
    }

    @RequestMapping(value = "/mqtt/push/message", method = RequestMethod.POST)
    public String index(@RequestBody String data) {
        publisher.publishMessage("sinosoft/topic", data);
        return "Success";
    }

}
