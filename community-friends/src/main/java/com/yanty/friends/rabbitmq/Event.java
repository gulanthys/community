package com.yanty.friends.rabbitmq;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Event {

    /**
     * 事件绑定路由
     */
    private String topic;

    /**
     * 事件数据
     */
    private final Map<String, Object> data = new HashMap<>();


}
