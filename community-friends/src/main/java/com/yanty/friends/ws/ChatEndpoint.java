package com.yanty.friends.ws;


import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Component
public class ChatEndpoint {

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    /**
     * 建立websocket连接后被调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        // 1. 保存session

        // 2. 广播消息。
    }

    /**
     *
     * @param session
     */
    @OnMessage
    public void onMessage(Session session){

    }

    @OnClose
    public void onClose(Session session){

    }

}
