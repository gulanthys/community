package org.gulanthys.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_uid_role")
public class UidRole implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.NONE)
    private Integer uid;
    /**
     * 角色id
     */
    @TableId(type = IdType.NONE)
    private Integer roleId;

}
