package org.gulanthys.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 使用状态
     */
    private Integer status;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
