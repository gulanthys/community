package org.love_156.document.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edit_permission")
public class EditPermission {
    /**
     * 文章id
     */
    private Integer ArticleId;
    /**
     * 用户id
     */
    private Integer UserId;
    /**
     *  权限状态
     *  1:作者
     *  2:邀请者
     *  3:被移除的邀请者
     */
    private Integer Permission;

}
