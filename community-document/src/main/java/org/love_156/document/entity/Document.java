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
@TableName(value = "doc_name")
public class Document implements Serializable {

    /**
     * 作者Id
     */
    @TableId(type = IdType.AUTO)
    private Integer AuthorID;
    /**
     * 文章标题
     */
    private String Title;
    /**
     * 最后编辑时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date EditDate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date CreatDate;
    /**
     * 使用状态 启用：0 停用：1
     */
    private Integer status;
    /**
     * 文章内容
     */
    private String Text;

}
