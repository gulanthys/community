package com.yanty.friends.ws;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.config.GetHttpSessionConfig;
import com.yanty.friends.utils.WebsocketUtils;
import com.yanty.friends.ws.pojo.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    //所有在线用户 <username,session>
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private HttpSession httpSession;

    /**
     * 建立websocket连接后被调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        // 1. 保存session
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String username = (String) this.httpSession.getAttribute("username");
        onlineUsers.put(username,session);
        // 2. 广播消息。
        //所有在线用户
        String users = JSONObject.toJSONString(onlineUsers.entrySet());
        sendAllMessage(users);
        log.info("有新用户加入,username={},当前在线人数：{}",username,onlineUsers.size());
    }

    /**
     * 广播消息
     */
    private void sendAllMessage(String message){
        //遍历map集合
        onlineUsers.forEach((key, session) -> {
                //发送消息
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * 客户端发送消息到服务端，该方法调用
     * @param message
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        //格式化JSON字符串为Java对象
        Message msg = JSON.parseObject(message, Message.class);
        //编辑message
        String resultMessage = WebsocketUtils.getMessage(false, msg.getToName(), msg.getContent());
        //获取接收方session
        Session session = onlineUsers.get(msg.getToName());
        //发送消息
        session.getBasicRemote().sendText(resultMessage);
    }

    /**
     * 断开websocket时被调用
     * @param session
     */
    @OnClose
    public void onClose(Session session, EndpointConfig config){
        //从map在线用户集合中剔除
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String username = (String) this.httpSession.getAttribute("username");
        onlineUsers.remove(username);
        //广播消息，通知当先用户下线
        String users = JSONObject.toJSONString(onlineUsers.entrySet());
        sendAllMessage(users);
        log.info("有用户退出,username：{},当前在线人数：{}",username,onlineUsers.size());

    }

}
