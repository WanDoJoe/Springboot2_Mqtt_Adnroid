package com.exmqtt.demomqtt.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController
public class MqttPushServices  {

    @Autowired
    MsgWriter msgWriter;
    @RequestMapping(value = "/sendmsg/{msg}")
    public void sendMsg(@PathVariable(value = "msg")String msg){

        msgWriter.sendToMqtt("asdasdasdasd");
    }
    @RequestMapping(value = "/sendmsgMT/{msg}/{topic}")
    public Object sendMsg(@PathVariable(value = "msg")String msg,@PathVariable (value = "topic")String topic){
        log.info("msg="+msg+" ;topic="+topic);
        msgWriter.sendToMqtt(msg,"sinosoft/topic");
        return "Ok";
    }

    @RequestMapping(value = "/sendmsgFull/{qos}/{msg}/{topic}")
    public void sendMsg(@PathVariable(value = "msg")String msg,@PathVariable (value = "topic")String topic
        ,@PathVariable("qos")String qos){
        msgWriter.sendToMqtt("asdasdasdasd");
    }
}
