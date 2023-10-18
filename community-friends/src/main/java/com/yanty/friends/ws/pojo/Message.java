package com.yanty.friends.ws.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    /**
     * 消息接受者
     */
    private String toName;

    /**
     * 内容
     */
    private String content;

}
