package com.yanty.friends.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EventProducer implements RabbitMQConstant{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发布事件
     */
    public void publishEvent(Event event){
        //发布事件
        rabbitTemplate.convertAndSend(event.getTopic(), JSON.toJSONString(event));
    }



}


