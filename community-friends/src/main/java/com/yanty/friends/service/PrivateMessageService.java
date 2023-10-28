package com.yanty.friends.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yanty.friends.entity.PrivateMessage;
import com.yanty.friends.ws.pojo.ResultMessage;

public interface PrivateMessageService  extends IService<PrivateMessage> {

    /**
     * 系统通知（个人）
     */
    Boolean systemNoticeIndividual(String receiverId, String message);

    /**
     * 系统通知（群发）
     */
    Boolean systemNoticeBroadCast(String message);
}
