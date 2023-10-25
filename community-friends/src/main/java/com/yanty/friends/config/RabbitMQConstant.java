package com.yanty.friends.config;


/**
 * RabbitMQ用到的常量
 */
public interface RabbitMQConstant {

    /**
     * RabbitMQ队列主题名称
     */
    String RABBITMQ_DEMO_TOPIC = "rabbitMQDemo";

    /**
     * RabbitMQ的DIRECT交换机名称
     */
    String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";

    /**
     * RabbitMQ的DIRECT交换机和队列绑定的匹配键DirectRouting
     */
    String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";
}
