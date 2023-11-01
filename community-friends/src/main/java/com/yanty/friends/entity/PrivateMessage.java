package com.yanty.friends.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "private_message")
public class PrivateMessage {

  /**
   * 消息id
   */
  private long id;

  /**
   * 作者
   */
  private String senderId;

  /**
   * 读者
   */
  private String receiverId;

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
  private int status;

  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;



}
