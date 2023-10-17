package org.gulanthys.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色功能关联
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.NONE)
    private Integer roleId;
    /**
     * 功能id
     */
    @TableId(type = IdType.NONE)
    private Integer menuId;
}
