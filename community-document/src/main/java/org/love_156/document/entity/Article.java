package org.love_156.document.entity;

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
@TableName(value = "community_document")
public class Article implements Serializable {

    /**
     * 文章Id
     */
    @TableId(type = IdType.AUTO)
    private Integer articleId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 最后编辑时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date editDate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date creatDate;
    /**
     * 使用状态 启用：0 停用：1
     */
    private Integer status;
    /**
     * 文章内容
     */
    private String text;

}
