package com.yanty.friends.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 消费者监听队列
 */
@Component
@Slf4j
public class Consumer implements RabbitMQConstant {

    /**
     * 监听队列
     */
    @RabbitListener(queues = PRIVATE_MESSAGE_TOPIC)
    public void process(Message message){
        byte[] body = message.getBody();
        log.info("收到的消息：{}",new String(body));


    }
}
