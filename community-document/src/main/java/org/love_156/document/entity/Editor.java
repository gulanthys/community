package org.love_156.document.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "editor")
public class Editor {
    /**
     * 文章id
     */
    private Integer DocumentId;
    /**
     * 用户id
     */
    private Integer UserId;

}
