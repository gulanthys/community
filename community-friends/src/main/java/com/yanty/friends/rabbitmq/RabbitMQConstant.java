package com.yanty.friends.rabbitmq;


/**
 * RabbitMQ用到的常量
 */
public interface RabbitMQConstant {

    /**
     * 私信：
     * 队列主题、交换机名称、Routing-Key
     */
    String PRIVATE_MESSAGE_TOPIC = "private_message_topic";
    String PRIVATE_MESSAGE_EXCHANGE = "private_message_exchange";
    String PRIVATE_MESSAGE_ROUTING = "private_message_routing";


    /**
     * 系统通知（点对点）
     * 队列主题、交换机名称、Routing-Key
     */
    String SYSTEM_NOTICE_INDIVIDUAL_TOPIC = "system_notice_individual_topic";
    String SYSTEM_NOTICE_INDIVIDUAL_EXCHANGE = "system_notice_individual_exchange";
    String SYSTEM_NOTICE_INDIVIDUAL_ROUTING = "system_notice_individual_routing";


    /**
     * 系统通知（群发通知）
     * 队列主题、交换机名称、Routing-Key
     */
    String SYSTEM_NOTICE_BROADCAST_TOPIC = "system_notice_broadcast_topic";
    String SYSTEM_NOTICE_BROADCAST_EXCHANGE = "system_notice_broadcast_exchange";
    String SYSTEM_NOTICE_BROADCAST_ROUTING = "system_notice_broadcast_routing";


}
