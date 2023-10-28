package com.yanty.friends.ws.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage {

    /**
     * 是否为系统通知
     */
    private boolean isSystemMessage;

    /**
     * 消息发送者
     */
    private String senderId;

    /**
     * 内容
     */
    private Object content;

}
