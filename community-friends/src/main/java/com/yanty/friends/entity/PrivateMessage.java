package com.yanty.friends.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
//@TableName(value = "private_message")
public class PrivateMessage {

  /**
   * 消息id
   */
  private long id;
  /**
   * 作者
   */
  private long authorId;
  /**
   * 读者
   */
  private long readerId;
  /**
   * 会话id
   */
  private String conversationId;
  /**
   * 内容
   */
  private String content;
  /**
   * 状态
   * 0：未读  1：已读  2：删除
   */
  private long status;
  /**
   * 创建时间
   */
  private Timestamp createTime;



}
