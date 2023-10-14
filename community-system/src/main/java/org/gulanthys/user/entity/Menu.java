package org.gulanthys.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
