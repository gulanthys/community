package com.yanty.friends.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanty.friends.FeignClient.UserFeignClient;
import com.yanty.friends.entity.PrivateMessage;
import com.yanty.friends.entity.User;
import com.yanty.friends.mapper.PrivateMessageMapper;
import com.yanty.friends.rabbitmq.Event;
import com.yanty.friends.rabbitmq.EventProducer;
import com.yanty.friends.rabbitmq.RabbitMQConstant;
import com.yanty.friends.service.PrivateMessageService;
import com.yanty.friends.utils.WebsocketUtils;
import org.community.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrivateMessageServiceImpl extends ServiceImpl<PrivateMessageMapper, PrivateMessage> implements PrivateMessageService, RabbitMQConstant {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private PrivateMessageMapper privateMessageMapper;
    @Autowired
    private EventProducer eventProducer;


    @Override
    public Boolean systemNoticeIndividual(String receiverId, String message) {
        //构建私信消息实体
        PrivateMessage pm = new PrivateMessage();
        pm.setSenderId("system");
        pm.setReceiverId(receiverId);
        pm.setConversationId("system_" + receiverId);
        pm.setContent(message);
        pm.setStatus(0);
        privateMessageMapper.insert(pm);
        //构建并发送RabbitMQ事件
        Event event = new Event();
        event.setTopic(SYSTEM_NOTICE_INDIVIDUAL_TOPIC);
        event.getData().put("receiverId",receiverId);
        event.getData().put("message", WebsocketUtils.getMessage(true, null, message));
        eventProducer.publishEvent(event);
        return true;
    }


    @Override
    public Boolean systemNoticeBroadCast(String message) {

//        //请求用户列表
//        String userListJson = userFeignClient.userList();
//        Result res = JSONObject.parseObject(userListJson, Result.class);
//        List<User> userList = JSONObject.parseObject(res.getData().toString(), new TypeReference<ArrayList<User>>() {});
//        List<PrivateMessage> privateMessageList = new ArrayList<>();
//        //构建私信消息持久化到mysql
//        userList.forEach(user -> {
//            PrivateMessage pm = new PrivateMessage();
//            pm.setSenderId("system");
//            pm.setReceiverId(user.getUid().toString());
//            pm.setConversationId("system" + user.getUid().toString());
//            pm.setContent(message);
//            pm.setStatus(0);
//            pm.setCreateTime(new Timestamp(new Date().getTime()));
//            privateMessageList.add(pm);
//        });
//        privateMessageMapper.insertBatch(privateMessageList);

        //构建事件发送到RabbitMQ处理
        Event event = new Event();
        event.setTopic(SYSTEM_NOTICE_BROADCAST_TOPIC);
        event.getData().put("message",WebsocketUtils.getMessage(true, null, message));
        eventProducer.publishEvent(event);

        return null;
    }
}
