package org.gulanthys.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 功能菜单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class Menu implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -1437955711962383192L;
    /**
     * 功能id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 功能名称
     */
    private String menuName;
    /**
     * 功能接口路由
     */
    private String path;
    /**
     * 功能标识
     */
    private String perms;
    /**
     * 状态 启用：0  停用：1
     */
    private Integer status;
}
