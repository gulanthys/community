package com.yanty.friends.ws.service;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


/**
 * message统一格式
 * 前端 --> 后端：
 * {
 *     "receiverId": "user456",
 *     "content": "你好，今晚一起吃晚饭吗？"
 * }
 *
 * 后端 --> 前端：
 * {
 *     “isSystemMessage”: "false",
 *     "senderId": "user123",
 *     "receiverId": "user456",
 *     "content": "你好，今晚一起吃晚饭吗？"
 * }
 *
 */
public interface WebSocketService {
    /**
     * 会话开始回调
     *
     * @param session 会话
     */
    void handleOpen(WebSocketSession session) throws IOException;

    /**
     * 会话结束回调
     *
     * @param session 会话
     */
    void handleClose(WebSocketSession session) throws IOException;

    /**
     * 处理消息
     *
     * @param session 会话
     * @param message 接收的消息
     */
    void handleMessage(WebSocketSession session, String message) throws IOException;

    /**
     * 发送消息
     *
     * @param session 当前会话
     * @param message 要发送的消息
     * @throws IOException 发送io异常
     */
    void sendMessage(WebSocketSession session, String message) throws IOException;

    /**
     * 发送消息
     *
     * @param receiverId  接收者id
     * @param message 要发送的消息
     * @throws IOException 发送io异常
     */
    void sendMessage(String receiverId, TextMessage message) throws IOException;

    /**
     * 发送消息
     *
     * @param receiverId  接收者id
     * @param message 要发送的消息
     * @throws IOException 发送io异常
     */
    void sendMessage(String receiverId, String message) throws IOException;

    /**
     * 发送消息
     *
     * @param session 当前会话
     * @param message 要发送的消息
     * @throws IOException 发送io异常
     */
    void sendMessage(WebSocketSession session, TextMessage message) throws IOException;

    /**
     * 广播
     *
     * @param message 字符串消息
     * @throws IOException 异常
     */
    void broadCast(String message) throws IOException;

    /**
     * 广播
     *
     * @param message 文本消息
     * @throws IOException 异常
     */
    void broadCast(TextMessage message) throws IOException;

    /**
     * 处理会话异常
     *
     * @param session 会话
     * @param error   异常
     */
    void handleError(WebSocketSession session, Throwable error);

    /**
     * 得到当前连接数
     *
     * @return 连接数
     */
    int getConnectionCount();
}
