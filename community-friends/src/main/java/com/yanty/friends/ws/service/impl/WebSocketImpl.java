package com.yanty.friends.ws.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.ws.pojo.Message;
import com.yanty.friends.ws.pojo.ResultMessage;
import com.yanty.friends.ws.service.WebSocket;
import com.yanty.friends.ws.utils.WebsocketUtils;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class WebSocketImpl implements WebSocket {

    /**
     * 在线连接数（线程安全）
     */
    private final AtomicInteger connectionCount = new AtomicInteger(0);

    /**
     * 线程安全的无序集合（存储会话）
     */
//    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /**
     * 处理WebSocket连接建立时的回调方法
     * @param session 会话
     */
    @Override
    public void handleOpen(WebSocketSession session) throws IOException {
        String senderId = (String) session.getAttributes().get("uid");
        sessions.put(senderId, session);
        int count = connectionCount.incrementAndGet();
        //广播上线消息
        String message = WebsocketUtils.getMessage(true, null, sessions.keySet());
        broadCast(message);
        log.info("加入一个新用户：{}，当前在线人数：{}", senderId, count);
    }


    /**
     * 处理WebSocket连接关闭时的回调方法。
     * @param session 会话
     */
    @Override
    public void handleClose(WebSocketSession session) throws IOException {
        String uid = (String) session.getAttributes().get("uid");
        sessions.remove(uid);
        int count = connectionCount.decrementAndGet();
        //广播下线消息
        String message = WebsocketUtils.getMessage(true, null, sessions.keySet());
        broadCast(message);
        log.info("一位用户退出登录：{}，当前在线人数：{}", uid, count);
    }

    /**
     * 处理WebSocket接收到的消息
     * @param session 会话
     * @param message 接收的消息
     */
    @Override
    public void handleMessage(WebSocketSession session, String message) throws IOException {
        //获取消息发送者userId
        String senderId = (String) session.getAttributes().get("uid");
        //解析message
        Message msg = JSONObject.parseObject(message, Message.class);
        //编写json格式message
        String jsonMessage = WebsocketUtils.getMessage(false, senderId, msg.getContent());
        // 只处理前端传来的文本消息，并且直接丢弃了客户端传来的消息
        log.info("收到一个来自：{} 的消息：{}", senderId, message);
        sendMessage(msg.getReceiverId(), jsonMessage);
    }

    /**
     * 单点消息推送
     */
    @Override
    public void sendMessage(WebSocketSession session, String message) throws IOException {
        this.sendMessage(session, new TextMessage(message));
    }

    @Override
    public void sendMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(message);
    }

    /**
     * 给
     */
    @Override
    public void sendMessage(String receiverId, String message) throws IOException {
        this.sendMessage(receiverId, new TextMessage(message));
    }

    /**
     *
     * @param receiverId  接受者id
     * @param message 要发送的消息
     * @throws IOException
     */
    @Override
    public void sendMessage(String receiverId, TextMessage message) throws IOException {
        for (String userId : sessions.keySet()) {
            if (receiverId.equals(userId)){
                WebSocketSession session = sessions.get(userId);
                session.sendMessage(message);
            }
        }
    }


    /**
     * 广播消息
     * @param message 字符串消息
     * @throws IOException
     */
    @Override
    public void broadCast(String message) throws IOException {
        for (String userId : sessions.keySet()) {
            WebSocketSession session = sessions.get(userId);
            this.sendMessage(session, message);
        }
    }

    /**
     * 广播消息
     * @param message 字符串消息
     * @throws IOException
     */
    @Override
    public void broadCast(TextMessage message) throws IOException {
        for (String userId : sessions.keySet()) {
            WebSocketSession session = sessions.get(userId);
            session.sendMessage(message);
        }
    }

    @Override
    public void handleError(WebSocketSession session, Throwable error) {
        log.error("websocket error：{}，session id：{}", error.getMessage(), session.getId());
        log.error("", error);
    }

    @Override
    public int getConnectionCount() {
        return connectionCount.get();
    }
}