package com.yanty.friends.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.ws.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;



/**
 * 消费者监听队列
 */
@Component
@Slf4j
public class Consumer implements RabbitMQConstant {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 监听队列
     */
    @RabbitHandler
    @RabbitListener(queues = PRIVATE_MESSAGE_TOPIC)
    public void process(@Payload String message) throws IOException {
        Event event = JSON.parseObject(message, Event.class);
        String receiverId = (String) event.getData().get("receiverId");
        String msg = (String) event.getData().get("message");
        webSocketService.sendMessage(receiverId, msg);
        log.info("RabbitMQ监听到一个私信消息...");


        System.out.println(event);

    }
}
