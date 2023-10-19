package org.love_156.document.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 标题
     */
    private String Title;

    /**
     * 最后编辑时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date EditDate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date CreatDate;

    /**
     * 文章内容
     */
    private String Text;
}
