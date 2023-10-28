package com.yanty.friends.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.yanty.friends.ws.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 消费者监听队列
 */
@Component
@Slf4j
public class EventConsumer implements RabbitMQConstant {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 监听队列
     */
    @RabbitHandler
    @RabbitListener(queues = PRIVATE_MESSAGE_TOPIC)
    public void privateMessage(@Payload String message) throws IOException {
        Event event = JSON.parseObject(message, Event.class);
        String receiverId = (String) event.getData().get("receiverId");
        String msg = (String) event.getData().get("message");
        webSocketService.sendMessage(receiverId, msg);

    }

    @RabbitHandler
    @RabbitListener(queues = SYSTEM_NOTICE_INDIVIDUAL_TOPIC)
    public void systemNoticeIndividual(@Payload String message) throws IOException {
        Event event = JSON.parseObject(message, Event.class);
        String receiverId = (String) event.getData().get("receiverId");
        String msg = (String) event.getData().get("message");
        webSocketService.sendMessage(receiverId, msg);

    }

    @RabbitHandler
    @RabbitListener(queues = SYSTEM_NOTICE_BROADCAST_TOPIC)
    public void systemNoticeBroadcast(@Payload String message) throws IOException {
        Event event = JSON.parseObject(message, Event.class);
        String msg = (String) event.getData().get("message");
        webSocketService.broadCast(msg);
    }
}
