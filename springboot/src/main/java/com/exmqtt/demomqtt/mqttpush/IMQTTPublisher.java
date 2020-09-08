package com.exmqtt.demomqtt.mqttpush;

/**
 * Publisher推送者
 */
public interface IMQTTPublisher {
    /**
     * Publish message
     *
     * @param topic
     * @param message
     */
    public void publishMessage(String topic, String message);
    /**
     * Publish message
     *
     * @param topic
     * @param message
     */
    public void publishMessage(String topic, String message,int qos);
    /**
     * Disconnect MQTT Client
     */
    public void disconnect();
}
