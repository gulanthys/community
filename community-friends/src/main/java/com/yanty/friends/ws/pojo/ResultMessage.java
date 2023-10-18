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
    private boolean system;
    /**
     * 消息接收者
     */
    private String toName;
    /**
     * 内容
     */
    private String content;

}
