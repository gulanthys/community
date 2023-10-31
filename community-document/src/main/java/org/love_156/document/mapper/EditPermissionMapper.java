package org.love_156.document.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.love_156.document.entity.EditPermission;

import java.util.HashMap;

@Mapper
public interface EditPermissionMapper extends BaseMapper<EditPermission> {
    Integer findPermissionByArticleIdAndUserId(Integer articleId,Integer userId);

}
