package com.yanty.friends.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.ws.pojo.ResultMessage;

public class WebsocketUtils {

    /**
     * 拼接WebSocket消息（JSON）
     */
    public static String getMessage(boolean isSystemMessage, String senderId, Object message){
        ResultMessage resultMessage = new ResultMessage(isSystemMessage, senderId, message);
        return JSONObject.toJSONString(resultMessage);
    }

}
