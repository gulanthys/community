package org.gulanthys.system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 消息实体
 * 前端  -->  后端
 */
public class Message {

    /**
     * 消息接受者
     */
    private String receiverId;

    /**
     * 内容
     */
    private String content;

}
