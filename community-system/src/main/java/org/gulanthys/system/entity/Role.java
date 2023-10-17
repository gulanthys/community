package org.gulanthys.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role")
public class Role implements Serializable {
    /**
     * 角色编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色key
     */
    private String roleKey;
    /**
     * 状态 启用：0  停用：1
     */
    private Integer status;
}
