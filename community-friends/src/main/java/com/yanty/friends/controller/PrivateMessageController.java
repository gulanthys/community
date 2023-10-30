package com.yanty.friends.controller;


import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.service.PrivateMessageService;
import com.yanty.friends.ws.pojo.Message;
import lombok.extern.slf4j.Slf4j;
import org.community.common.Constants;
import org.community.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/systemNotice")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    /**
     * 系统消息单点推送
     * 示例：
     * receiverId：1, content："恭喜你的作品被点赞！"
     */
    @PostMapping("/individual")
    public Result<?> systemNoticeIndividual(String receiverId, String content){

        log.info("来自系统的消息：  receiverId:{}, content:{}",receiverId,content);

        if (receiverId == null || receiverId.equals("")){
            return Result.buildResult(Constants.Status.ERROR, "未指定消息接受人！");
        }
        if (content == null || content.equals("")){
            return Result.buildResult(Constants.Status.ERROR, "消息内容不能为空！");
        }
        privateMessageService.systemNoticeIndividual(receiverId, content);
        return Result.buildResult(Constants.Status.OK, "消息已发送！");
    }


    /**
     * 系统消息广播（公告）
     * 示例：
     * content：“服务器将于今晚11:00至次日凌晨4:00维护，请见谅。”
     */
    @PostMapping("/broadcast")
    public Result<?> systemNoticeBroadcast(String content){
        if (content == null || content.equals("")){
            return Result.buildResult(Constants.Status.ERROR, "消息内容不能为空！");
        }
        privateMessageService.systemNoticeBroadCast(content);
        return Result.buildResult(Constants.Status.OK, "消息已发送！");
    }



}
