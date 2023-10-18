package com.yanty.friends.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.ws.pojo.ResultMessage;

public class WebsocketUtils {

    /**\
     * 拼接WebSocket消息（JSON）
     * @param isSystemMessage
     * @param toName
     * @param message
     * @return
     */
    public static String getMessage(boolean isSystemMessage, String toName, String message){
        ResultMessage resultMessage = new ResultMessage(isSystemMessage, toName, message);
        return JSONObject.toJSONString(resultMessage);
    }

}
