package com.yanty.friends.rabbitmq;


/**
 * RabbitMQ用到的常量
 */
public interface RabbitMQConstant {

    /**
     * 队列主题：私信
     */
    String PRIVATE_MESSAGE_TOPIC = "private_message_topic";

    /**
     * 交换机名称：私信
     */
    String PRIVATE_MESSAGE_EXCHANGE = "private_message_exchange";

    /**
     * Routing-Key：私信
     */
    String PRIVATE_MESSAGE_ROUTING = "private_message_routing";
}
