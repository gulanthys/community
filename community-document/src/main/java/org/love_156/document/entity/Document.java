package org.love_156.document.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

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
    public Integer AuthorID;

    /**
     * 标题
     */
    public String Title;

    /**
     * 最后编辑时间
     */
    public Date EditDate;
    /**
     * 创建时间
     */
    public Date CreatDate;

    /**
     * 文章内容
     */
    public  String Text;
}
