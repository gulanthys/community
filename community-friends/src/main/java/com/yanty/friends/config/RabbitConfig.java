package com.yanty.friends.config;

import com.yanty.friends.rabbitmq.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig implements RabbitMQConstant {

    /**
     * 声明队列
     *
     */
    @Bean
    public Queue rabbitmqQueue() {
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * */
        return new Queue(PRIVATE_MESSAGE_TOPIC, true, false, false);
    }

    /**
     * 声明交换机
     *
     */
    @Bean
    public TopicExchange topicExchange() {
        //Direct交换机
        return new TopicExchange(PRIVATE_MESSAGE_EXCHANGE, true, false);
    }

    /**
     * 声明绑定关系
     *
     */
    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
        //绑定队列
        .bind(rabbitmqQueue())
        //到交换机
        .to(topicExchange())
        //routing-key
        .with(PRIVATE_MESSAGE_ROUTING);
    }
}