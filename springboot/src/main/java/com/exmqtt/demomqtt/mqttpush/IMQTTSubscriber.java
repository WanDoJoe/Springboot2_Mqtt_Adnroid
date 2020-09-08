package com.exmqtt.demomqtt.mqttpush;

/**
 *  Subscriber 订阅者
 *  接收推送
 */
public interface IMQTTSubscriber {

    /**
     * Subscribe message
     *
     * @param topic
     */
    public void subscribeMessage(String topic);

    /**
     * Disconnect MQTT Client
     */
    public void disconnect();
}

